package com.yahoo.marshallmoore55.playerholograms.Util;

import com.miguelmeep.dev.api.DB;
import com.yahoo.marshallmoore55.playerholograms.Main;

import java.sql.*;
import java.util.UUID;

public class PermissionsDatabase {

    public PermissionsDatabase() {
        try {
            Connection conn = Main.getInstance().getDB().getConnection();
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pholoPerm (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, admin_uuid CHAR(36))");
            ps.executeUpdate();
            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addAdmin(UUID admin_uuid) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pholoPerm VALUES(null,?)");
            ps.setString(1, admin_uuid.toString());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void removeAdmin(UUID admin_uuid) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM pholoPerm WHERE admin_uuid='" + admin_uuid.toString() + "'");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isAdmin(UUID player_uuid) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT admin_uuid from pholoPerm");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("admin_uuid").equals(player_uuid.toString())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
