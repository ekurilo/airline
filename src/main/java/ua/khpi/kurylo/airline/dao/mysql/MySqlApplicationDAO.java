package ua.khpi.kurylo.airline.dao.mysql;

import org.apache.log4j.Logger;
import ua.khpi.kurylo.airline.dao.ApplicationDAO;
import ua.khpi.kurylo.airline.entity.Application;
import ua.khpi.kurylo.airline.entity.status.ApplicationStatus;
import ua.khpi.kurylo.airline.exception.DBException;
import ua.khpi.kurylo.airline.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.khpi.kurylo.airline.db.Fields.*;

public class MySqlApplicationDAO implements ApplicationDAO {
    private static final Logger LOG = Logger.getLogger(MySqlApplicationDAO.class);

    @Override
    public Application getApplicationById(Integer id) throws DBException {
        Application app = null;
        String sql = "SELECT * FROM applications WHERE id=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        app = extractApplication(rs);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_APP_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_APP_BY_ID, e);
        }
        return app;
    }

    @Override
    public List<Application> getApplications() throws DBException {
        String sql = "SELECT * FROM applications;";
        return getListApps(sql);
    }

    @Override
    public List<Application> getOpenedApplications() throws DBException {
        String sql = "SELECT * FROM applications WHERE status_id=0;";
        return getListApps(sql);
    }

    @Override
    public void changeStatus(Integer id, ApplicationStatus status) throws DBException {
        String sql = "UPDATE applications SET status_id=? WHERE id=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, status.ordinal());
                st.setInt(2, id);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CHANGE_APP_STATUS, e);
            throw new DBException(Messages.ERR_CANNOT_CHANGE_APP_STATUS, e);
        }
    }

    @Override
    public void addApplication(Application app) throws DBException {

        String sql = "INSERT INTO applications (title, brigade_id, status_id) VALUES(?,?,?);";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, app.getMessage());
                st.setInt(index++, app.getBrigadeId());
                st.setInt(index, app.getStatusId());
                st.executeUpdate();

            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_ADD_APP, e);
            throw new DBException(Messages.ERR_CANNOT_ADD_APP, e);
        }

    }

    private Application extractApplication(ResultSet rs) throws SQLException {
        Application app = new Application();
        app.setId(rs.getInt(APPLICATIONS_ID));
        app.setMessage(rs.getString(APPLICATIONS_TITLE));
        app.setBrigadeId(rs.getInt(APPLICATIONS_BRIGADE_ID));
        app.setStatusId(rs.getInt(APPLICATIONS_STATUS_ID));
        return app;
    }

    private List<Application> getListApps(String sql) throws DBException {
        List<Application> apps = new ArrayList<>();
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection(); Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    Application app = extractApplication(rs);
                    apps.add(app);
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_APPS, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_APPS, e);
        }
        return apps;
    }

}
