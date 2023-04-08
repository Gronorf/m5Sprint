package mysql;

import conexion.ConexionSingleton;
import dao.AdministrativoDAO;
import dao.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Administrativo;

/**
 *
 * @author Leonel Briones Palacios
 */
public class MySQLAdministrativoDAO implements AdministrativoDAO {

    final String INSERT = "INSERT INTO administrativos(run, nombres, area, expPrevia)VALUES(?, ?, ?, ?);";
    final String UPDATE = "UPDATE administrativos SET run = ?, nombres = ?, area = ?, expPrevia = ? WHERE run = ?;";
    final String DELETE = "DELETE FROM administrativos WHERE run = ?;";
    final String GETALL = "SELECT * FROM administrativos;";
    final String GETONE = "SELECT * FROM administrativos WHERE run = ?;";

    private Connection conn;

    public MySQLAdministrativoDAO(Connection conn) {
        this.conn = ConexionSingleton.getConexion();
    }

    @Override
    public void insertar(Administrativo t) throws DAOException {
        //CREAR PREPAREDSTATEMENT
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(INSERT);

            st.setInt(1, (int) t.getRun());
            st.setString(2, t.getNombreUsuario());
            st.setString(3, t.getArea());
            st.setString(4, t.getExperienciaPrevia());

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
    public void modificar(Administrativo t) throws DAOException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(UPDATE);

            st.setInt(1, (int) t.getRun());
            st.setString(2, t.getNombreUsuario());
            st.setString(3, t.getArea());
            st.setString(4, t.getExperienciaPrevia());

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
    public void eliminar(Administrativo t) throws DAOException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(DELETE);

            st.setInt(1, (int) t.getRun());

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

    private Administrativo convertirObjeto(ResultSet rs) throws SQLException {

        Administrativo administrativo = new Administrativo();

        administrativo.setRun(rs.getInt("run"));
        administrativo.setNombreUsuario(rs.getString("nombres"));
        administrativo.setArea(rs.getString("area"));
        administrativo.setExperienciaPrevia(rs.getString("expPrevia"));

        return administrativo;
    }

    @Override
    public List<Administrativo> obtenerTodos() throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Administrativo> listaAdministrativo = new ArrayList<>();

        try {
            st = conn.prepareStatement(GETALL);
            rs = st.executeQuery();
            while (rs.next()) {
                listaAdministrativo.add(convertirObjeto(rs));
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
        return listaAdministrativo;
    }

    @Override
    public Administrativo obtener(Integer id) throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        Administrativo administrativo = new Administrativo();

        try {
            st = conn.prepareStatement(GETONE);
            rs = st.executeQuery();
            while (rs.next()) {
                administrativo = convertirObjeto(rs);
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
        return administrativo;
    }

}
