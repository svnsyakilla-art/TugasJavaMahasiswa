/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Ahmad Yusuf
 */
public class Mahasiswa {
    int id;
    String nim;
    String nama;
    
    int tahunMasuk;
    Connection con;
    
    public Mahasiswa() {}
    
    Mahasiswa(int id, String nim, String nama, int tahunMasuk){
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.tahunMasuk = tahunMasuk;
        
        con = DbConnection.connect();
    }
    
    Mahasiswa(String nim, String nama, int tahunMasuk){
        this.nama = nama;
        this.nim = nim;
        this.tahunMasuk = tahunMasuk;
    }
    public int getId() { return id; }
    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public int getTahunMasuk() { return tahunMasuk; }
    
    public boolean insert() {
        try (Connection con = DbConnection.connect()) {
            String sql = "INSERT INTO mahasiswa (nim, nama) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, this.nim);
            pst.setString(2, this.nama);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        public static List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();
        try (Connection con = DbConnection.connect()) {
            String sql = "SELECT * FROM mahasiswa ORDER BY id ASC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Mahasiswa m = new Mahasiswa(
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    0
                );
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
          public boolean update(int id) {
        try (Connection con = DbConnection.connect()) {
            String sql = "UPDATE mahasiswa SET nim=?, nama=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, this.nim);
            pst.setString(2, this.nama);
            pst.setInt(3, id);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
           public static boolean delete(int id) {
        try (Connection con = DbConnection.connect()) {
            String sql = "DELETE FROM mahasiswa WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }   
    }      
}
