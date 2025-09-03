import React from 'react'
import { MdDelete, MdEdit } from 'react-icons/md';

const RedTabeleRestoran = ({id, naziv, adresa,onDelete,onUpdate}) => {
  return (
     <tr key={id}>
                <td>{id}</td>
                <td>{naziv}</td>
                <td>{adresa}</td>
                <td>
                    <button className="btn tiny danger" onClick={() => onDelete(id)}><MdDelete /></button>
                    <button className="btn tiny danger" onClick={() => onUpdate(id)}><MdEdit /></button>
                </td>
              </tr>
  );
}

export default RedTabeleRestoran