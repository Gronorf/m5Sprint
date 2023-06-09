package mysql;

import java.sql.*;

import java.util.List;
import modelo.Cliente;
import conexion.ConexionSingleton;
import dao.ClienteDAO;
import dao.DAOException;
import java.util.ArrayList;

public class MySQLClienteDAO implements ClienteDAO {

    final String INSERT = "INSERT INTO Cliente(rutCliente, cliNombres, cliApellidos, cliTelefono, cliAfp, cliSistemaSalud, cliDireccion, cliComuna)VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    final String UPDATE = "UPDATE Cliente SET rutCliente = ?, cliNombres = ?, cliApellidos = ?, cliTelefono = ?, cliAfp = ?, cliSistemaSalud = ?, cliDireccion = ?, cliComuna = ? WHERE idCliente;";
    final String DELETE = "DELETE FROM Cliente WHERE rutCliente = ?;";
    final String GETALL = "SELECT * FROM Cliente;";
    final String GETONE = "SELECT * FROM Cliente WHERE rutCliente = ?";

    private Connection conn;

    public MySQLClienteDAO(Connection conn) {
        this.conn = ConexionSingleton.getConexion();
    }

    @Override
    public void insertar(Cliente t) throws DAOException {
        //CREAR PREPAREDSTATEMENT
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(INSERT);
            
            st.setInt(1, t.getRut());
            st.setString(2, t.getNombre());
            st.setString(3, t.getApellido());
            st.setString(4, t.getTelefono());
            st.setString(5, t.getAfp());
            st.setString(6, t.getSistemaSalud());
            st.setString(7, t.getDireccion());
            st.setString(8, t.getComuna());

            if (st.executeUpdate() == 0) {
                throw new DAOException("No se ha guardado el Registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    @Override
    public void modificar(Cliente t) throws DAOException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(UPDATE);
            st.setInt(1, t.getRut());
            st.setString(2, t.getNombre());
            st.setString(3, t.getApellido());
            st.setString(4, t.getTelefono());
            st.setString(5, t.getAfp());
            st.setString(6, t.getSistemaSalud());
            st.setString(7, t.getDireccion());
            st.setString(8, t.getComuna());
            st.setInt(9, t.getId());

            if (st.executeUpdate() == 0) {
                throw new DAOException("No se ha guardado el Registro.");
            }

        }catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    @Override
    public void eliminar(Cliente t) throws DAOException {
        PreparedStatement st = null;
        
        try{
            st = conn.prepareStatement(DELETE);
            st.setInt(1, t.getRut());
        }catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    /**
     * Método que permite crear un objeto Cliente recibiendo un ResultSet como
     * parámetro
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Cliente convertirObjeto(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();

        cliente.setId(rs.getInt("idCliente"));
        cliente.setRut(rs.getInt("rutCliente"));
        cliente.setNombre(rs.getString("cliNombres"));
        cliente.setApellido(rs.getString("cliApellidos"));
        cliente.setTelefono(rs.getString("cliTelefono"));
        cliente.setAfp(rs.getString("cliAfp"));
        cliente.setSistemaSalud(rs.getString("cliSistemaSalud"));
        cliente.setDireccion(rs.getString("cliDireccion"));
        cliente.setComuna(rs.getString("cliComuna"));

        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() throws DAOException {

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> listaCliente = new ArrayList<>();

        try {
            st = conn.prepareStatement(GETALL);
            rs = st.executeQuery();
            while (rs.next()) {
                listaCliente.add(convertirObjeto(rs));
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    new DAOException("Error en SQL", ex);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    new DAOException("Error en SQL", ex);
                }
            }
        }
        return listaCliente;
    }

    @Override
    public Cliente obtener(Integer id) throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            st = conn.prepareStatement(GETONE);
            rs = st.executeQuery();
            while (rs.next()) {
                cliente = convertirObjeto(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    new DAOException("Error en SQL", ex);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    new DAOException("Error en SQL", ex);
                }
            }
        }
        return cliente;
    }

}
