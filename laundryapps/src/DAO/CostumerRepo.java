package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import confg.Database;
import model.Costumer;

public class CostumerRepo implements CostumerDAO {
    private Connection connection;

    public CostumerRepo() {
        this.connection = Database.getConnection();
        if (this.connection == null) {
            throw new IllegalStateException("Koneksi gagal! Periksa konfigurasi database.");
        }
    }

    @Override
    public void save(Costumer costumer) {
        String insert = "INSERT INTO costumer (nama, alamat, nomorHp) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNomorHp());
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CostumerRepo.class.getName()).log(Level.SEVERE, "Error inserting costumer", e);
        }
    }

    @Override
    public List<Costumer> show() {
        List<Costumer> costumers = new ArrayList<>();
        String select = "SELECT * FROM costumer";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                Costumer costumer = new Costumer();
                costumer.setId(rs.getString("id"));
                costumer.setNama(rs.getString("nama"));
                costumer.setAlamat(rs.getString("alamat"));
                costumer.setNomorHp(rs.getString("nomorHp"));
                costumers.add(costumer);
            }
        } catch (SQLException e) {
            Logger.getLogger(CostumerRepo.class.getName()).log(Level.SEVERE, "Error fetching costumers", e);
        }
        return costumers;
    }

    @Override
    public void update(Costumer costumer) {
        String update = "UPDATE costumer SET nama = ?, alamat = ?, nomorHp = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNomorHp());
            st.setString(4, costumer.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CostumerRepo.class.getName()).log(Level.SEVERE, "Error updating costumer", e);
        }
    }

    @Override
    public void delete(String id) {
        String delete = "DELETE FROM costumer WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CostumerRepo.class.getName()).log(Level.SEVERE, "Error deleting costumer", e);
        }
    }
}
