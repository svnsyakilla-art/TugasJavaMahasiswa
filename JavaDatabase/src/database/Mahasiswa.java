package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class Mahasiswa {

    public int id;
    public String nim;
    public String nama;
    public int tahunMasuk;

    public Mahasiswa(int id, String nama, String nim, int tahunMasuk) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.tahunMasuk = tahunMasuk;
    }

    // Constructor untuk insert baru
    public Mahasiswa(String nama, String nim, int tahunMasuk) {
        this.nama = nama;
        this.nim = nim;
        this.tahunMasuk = tahunMasuk;
    }

    // Getter
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public int getTahunMasuk() { return tahunMasuk; }

    // INSERT DATA
    public boolean insert() {
        String sql = "INSERT INTO mahasiswa (nama, nim) VALUES (?, ?)";
        try (Connection con = DbConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nama);
            ps.setString(2, this.nim);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // SELECT ALL
    public static List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();

        String sql = "SELECT * FROM mahasiswa ORDER BY id ASC";

        try (Connection con = DbConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Mahasiswa m = new Mahasiswa(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    0
                );
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public boolean update(int id) {
        String sql = "UPDATE mahasiswa SET nama=?, nim=? WHERE id=?";

        try (Connection con = DbConnection.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, this.nama);
            pst.setString(2, this.nim);
            pst.setInt(3, id);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public static boolean delete(int id) {
        String sql = "DELETE FROM mahasiswa WHERE id=?";

        try (Connection con = DbConnection.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    // UPLOAD CSV
    public static void uploadCSV(File file) throws Exception {
        String sql = "INSERT INTO mahasiswa (nama, nim) VALUES (?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             Connection con = DbConnection.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // skip header
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 2) {
                    String nama = data[0].trim();
                    String nim  = data[1].trim();

                    pst.setString(1, nama);
                    pst.setString(2, nim);
                    pst.addBatch();
                }
            }

            pst.executeBatch();

        } catch (Exception e) {
            throw new Exception("Gagal upload CSV: " + e.getMessage());
        }
    }
}
