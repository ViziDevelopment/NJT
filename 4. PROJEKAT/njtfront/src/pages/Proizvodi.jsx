
import React, { useEffect, useState } from "react";
import http from "../api/http";
import {
  Box,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Stack,
  TextField,
  Typography,
  Link,
} from "@mui/material";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import "../css/Proizvodi.css";

export default function Proizvodi() {
  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState("");

  // CRUD state
  const [open, setOpen] = useState(false);
  const [editing, setEditing] = useState(null); // null => create, {row} => edit

  // Forma
  const [form, setForm] = useState({
    name: "",
    description: "",
    price: "",
    imageUrl: "",
    restaurantId: "",
  });

  const columns = [
    { field: "id", headerName: "ID", width: 80 },
    { field: "name", headerName: "Name", flex: 1, minWidth: 140 },
    {
      field: "description",
      headerName: "Description",
      flex: 1.2,
      minWidth: 200,
      renderCell: (params) => <span>{params?.row?.description ?? ""}</span>,
    },
    {
      field: "price",
      headerName: "Price",
      width: 120,
      type: "number",
      align: "right",
      headerAlign: "right",
      valueFormatter: (p) => p.value
    },
    {
      field: "imageUrl",
      headerName: "Image/Link",
      flex: 1,
      minWidth: 220,
      sortable: false,
      renderCell: (params) => {
        const url = params?.row?.imageUrl || "";
        const looksLikeUrl = typeof url === "string" && url.startsWith("http");
        return (
          <Stack direction="row" spacing={1} alignItems="center" sx={{ overflow: "hidden" }}>
            {looksLikeUrl && (
              <img
                src={url}
                alt="product"
                onError={(e) => (e.currentTarget.style.display = "none")}
                style={{
                  width: 36,
                  height: 36,
                  objectFit: "cover",
                  borderRadius: 6,
                  border: "1px solid var(--stroke)",
                }}
              />
            )}
            {url ? (
              <Link
                href={url}
                target="_blank"
                rel="noopener noreferrer"
                underline="hover"
                sx={{
                  maxWidth: "100%",
                  overflow: "hidden",
                  textOverflow: "ellipsis",
                  whiteSpace: "nowrap",
                }}
              >
                {url}
              </Link>
            ) : (
              <span style={{ color: "var(--muted)" }}>—</span>
            )}
          </Stack>
        );
      },
    },
    {
      field: "actions",
      headerName: "Actions",
      width: 190,
      sortable: false,
      filterable: false,
      renderCell: (params) => (
        <Stack direction="row" spacing={1}>
          <Button size="small" variant="outlined" onClick={() => startEdit(params.row)}>
            Edit
          </Button>
          <Button
            size="small"
            color="error"
            variant="outlined"
            onClick={() => handleDelete(params.row.id)}
          >
            Delete
          </Button>
        </Stack>
      ),
    },
  ];

  // LOAD ALL (klijentski)
  useEffect(() => {
    setLoading(true);
    http
      .get("/product")
      .then((res) => {
        const data = Array.isArray(res.data) ? res.data : [];
        const safe = data
          .filter((x) => x && (x.id !== undefined && x.id !== null))
          .map((x) => ({
            id: x.id,
            name: x.name ?? "",
            description: x.description ?? "",
            // price normalizovan kao broj ili null, da Grid sigurno prikaže
            price:
              x.price === null || x.price === undefined || x.price === ""
                ? null
                : Number(x.price),
            imageUrl: x.imageUrl ?? "",
            restaurantId:
              x.restaurantId === null || x.restaurantId === undefined ? "" : x.restaurantId,
          }));
        setRows(safe);
      })
      .catch((e) => setErr(e?.response?.data?.message || e.message))
      .finally(() => setLoading(false));
  }, []);

  function openCreate() {
    setEditing(null);
    setForm({
      name: "",
      description: "",
      price: "",
      imageUrl: "",
      restaurantId: "",
    });
    setOpen(true);
  }

  function startEdit(row) {
    setEditing(row);
    setForm({
      name: row?.name ?? "",
      description: row?.description ?? "",
      price: row?.price === null || row?.price === undefined ? "" : String(row.price),
      imageUrl: row?.imageUrl ?? "",
      restaurantId:
        row?.restaurantId === null || row?.restaurantId === undefined
          ? ""
          : String(row.restaurantId),
    });
    setOpen(true);
  }

  function closeDialog() {
    setOpen(false);
    setEditing(null);
    setForm({
      name: "",
      description: "",
      price: "",
      imageUrl: "",
      restaurantId: "",
    });
  }

  async function handleSave(e) {
    e.preventDefault();
    const payload = {
      name: String(form.name ?? "").trim(),
      description: String(form.description ?? "").trim(),
      imageUrl: String(form.imageUrl ?? "").trim(),
      price:
        form.price === "" || form.price === null || form.price === undefined
          ? null
          : Number(form.price),
      restaurantId:
        form.restaurantId === "" || form.restaurantId === null || form.restaurantId === undefined
          ? null
          : Number(form.restaurantId),
    };

    if (!payload.name) {
      alert("Name is required.");
      return;
    }
    if (payload.price !== null && Number.isNaN(payload.price)) {
      alert("Price must be a number.");
      return;
    }

    try {
      if (editing == null) {
        // CREATE
        const res = await http.post("/product", payload);
        setRows((prev) => [
          ...prev,
          {
            id: res.data.id,
            name: res.data.name ?? "",
            description: res.data.description ?? "",
            price:
              res.data.price === null || res.data.price === undefined || res.data.price === ""
                ? null
                : Number(res.data.price),
            imageUrl: res.data.imageUrl ?? "",
            restaurantId:
              res.data.restaurantId === null || res.data.restaurantId === undefined
                ? ""
                : res.data.restaurantId,
          },
        ]);
      } else {
        // UPDATE
        const res = await http.put(`/product/${editing.id}`, { id: editing.id, ...payload });
        setRows((prev) =>
          prev.map((r) =>
            r.id === editing.id
              ? {
                  id: res.data.id,
                  name: res.data.name ?? "",
                  description: res.data.description ?? "",
                  price:
                    res.data.price === null ||
                    res.data.price === undefined ||
                    res.data.price === ""
                      ? null
                      : Number(res.data.price),
                  imageUrl: res.data.imageUrl ?? "",
                  restaurantId:
                    res.data.restaurantId === null || res.data.restaurantId === undefined
                      ? ""
                      : res.data.restaurantId,
                }
              : r
          )
        );
      }
      closeDialog();
    } catch (e) {
      alert(e?.response?.data?.message || e.message);
    }
  }

  async function handleDelete(id) {
    if (!window.confirm("Obrisati proizvod?")) return;
    try {
      await http.delete(`/product/${id}`);
      setRows((prev) => prev.filter((r) => r.id !== id));
    } catch (e) {
      alert(e?.response?.data?.message || e.message);
    }
  }

  return (
    <Box sx={{ p: 2 }}>
      <Stack direction="row" alignItems="center" justifyContent="space-between" sx={{ mb: 2 }}>
        <Box>
          <Typography variant="h4">Products</Typography>
          <Typography variant="body2" color="text.secondary">
            Klijentska paginacija/sort/filter — sve se učitava sa bekenda jednom.
          </Typography>
        </Box>
        <Button variant="contained" onClick={openCreate}>
          + New Product
        </Button>
      </Stack>

      {err && (
        <Box sx={{ mb: 2, color: "error.main" }}>
          <Typography variant="body2">{err}</Typography>
        </Box>
      )}

      <div className="dg-card" style={{ height: 560, width: "100%" }}>
        <DataGrid
          rows={rows}
          columns={columns}
          loading={loading}
          disableRowSelectionOnClick
          initialState={{
            pagination: { paginationModel: { page: 0, pageSize: 10 } },
          }}
          pageSizeOptions={[5, 10, 25, 50, 100]}
          slots={{ toolbar: GridToolbar }}
          slotProps={{
            toolbar: { showQuickFilter: true, quickFilterProps: { debounceMs: 300 } },
          }}
        />
      </div>

      {/* CREATE / EDIT dialog */}
      <Dialog open={open} onClose={closeDialog} maxWidth="sm" fullWidth>
        <form onSubmit={handleSave}>
          <DialogTitle>{editing ? "Edit product" : "New product"}</DialogTitle>
          <DialogContent>
            <Stack spacing={2} sx={{ mt: 1 }}>
              <TextField
                label="Name"
                value={form.name}
                onChange={(e) => setForm((f) => ({ ...f, name: e.target.value }))}
                autoFocus
                fullWidth
                required
              />
              <TextField
                label="Description"
                value={form.description}
                onChange={(e) => setForm((f) => ({ ...f, description: e.target.value }))}
                fullWidth
                multiline
                minRows={2}
              />
              <TextField
                label="Price"
                type="number"
                value={form.price}
                onChange={(e) => setForm((f) => ({ ...f, price: e.target.value }))}
                fullWidth
              />
              <TextField
                label="Image URL"
                value={form.imageUrl}
                onChange={(e) => setForm((f) => ({ ...f, imageUrl: e.target.value }))}
                fullWidth
              />
              <TextField
                label="Restaurant ID"
                type="number"
                value={form.restaurantId}
                onChange={(e) => setForm((f) => ({ ...f, restaurantId: e.target.value }))}
                fullWidth
              />
            </Stack>
          </DialogContent>
          <DialogActions>
            <Button onClick={closeDialog}>Cancel</Button>
            <Button type="submit" variant="contained">
              Save
            </Button>
          </DialogActions>
        </form>
      </Dialog>
    </Box>
  );
}
