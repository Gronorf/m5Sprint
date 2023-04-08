package mysql;

import conexion.ConexionSingleton;
import dao.DAOException;
import dao.ProfesionalDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Profesional;

/**
 *
 * @author Leonel Briones Palacios
 */
public class MySQLProfesionalDAO implements ProfesionalDAO {

    final String INSERT = "INSERT INTO profesionales(rut, nombres, tituloProfesional, fechaIngreso) VALUES(?, ?, ?, ?);";
    final String UPDATE = "UPDATE profesionales SET rut = ?, nombres = ?, tituloProfesional = ?, fechaIngreso = ? WHERE rut = ?;";
    final String DELETE = "DELETE FROM profesionales WHERE rut = ?;";
    final String GETALL = "SELECT * FROM profesionales;";
    final String GETONE = "SELECT * FROM profesionales WHERE rut = ?;";

    private Connection conn;

    public MySQLProfesionalDAO(Connection conn) {
        this.conn = ConexionSingleton.getConexion();
    }

    @Override
    public void insertar(Profesional t) throws DAOException {
        //CREAR PREPAREDSTATEMENT
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(INSERT);

            st.setLong(1, t.getRun());
            st.setString(2, t.getNombreUsuario());
            st.setString(3, t.getTitulo());
            st.setDate(4, (Date) t.getFechaIngreso());

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
    public void modificar(Profesional t) throws DAOException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(UPDATE);
            st.setLong(1, t.getRun());
            st.setString(2, t.getNombreUsuario());
            st.setString(3, t.getTitulo());
            st.setDate(4, (Date) t.getFechaIngreso());
            st.setInt(5, (int) t.getRun());

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
    public void eliminar(Profesional t) throws DAOException {
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

    private Profesional convertirObjeto(ResultSet rs) throws SQLException {

        Profesional profesional = new Profesional();

        profesional.setRun(rs.getLong("rut"));
        profesional.setNombreUsuario(rs.getString("nombres"));
        profesional.setFechaIngreso(rs.getDate("fechaIngreso"));
        profesional.setTitulo(rs.getString("tituloProfesional"));

        return profesional;
    }

    @Override
    public List<Profesional> obtenerTodos() throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Profesional> listaProfesional = new ArrayList<>();

        try {
            st = conn.prepareStatement(GETALL);
            rs = st.executeQuery();
            while (rs.next()) {
                listaProfesional.add(convertirObjeto(rs));
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
        return listaProfesional;
    }

    @Override
    public Profesional obtener(Integer id) throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        Profesional profesional = new Profesional();

        try {
            st = conn.prepareStatement(GETONE);
            rs = st.executeQuery();
            while (rs.next()) {
                profesional = convertirObjeto(rs);
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
        return profesional;
    }
}

