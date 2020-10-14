package com.yahoo.marshallmoore55.playerholograms.Util;

import com.miguelmeep.dev.api.DB;
import com.yahoo.marshallmoore55.playerholograms.Main;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class HologramDatabase {


    public HologramDatabase() {
        try {
            Connection conn = Main.getInstance().getDB().getConnection();
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pholo (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, holo_uuid CHAR(36), placer_uuid CHAR(36), owner_uuid CHAR(36), owner_name CHAR(36))");
            ps.executeUpdate();
            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addHolo(Player placer, UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pholo VALUES(NULL,?,?,NULL,NULL)");
            ps.setString(1, holoId.toString());
            ps.setString(2, placer.getUniqueId().toString());
            ps.executeUpdate();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeHolo(UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM pholo WHERE holo_uuid='" + holoId.toString() + "'");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rentHolo(Player owner, UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("UPDATE pholo SET owner_uuid='" + owner.getUniqueId().toString() + "' , owner_name='" + owner.getDisplayName() + "' WHERE holo_uuid='" + holoId.toString() + "'");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unrentHolo(UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("UPDATE pholo SET owner_uuid=NULL , owner_name=NULL WHERE holo_uuid='" + holoId.toString() + "'");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean contains(UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT holo_uuid from pholo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("holo_uuid").equals(holoId.toString())) {
                    return true;
                }
            }
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isOwned(UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT owner_uuid from pholo WHERE holo_uuid='" + holoId.toString() + "'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                rs.getString("owner_uuid");
                if (!rs.wasNull()) {
                    return true;
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getOwner(UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT owner_uuid from pholo WHERE holo_uuid='" + holoId.toString() + "'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String result = rs.getString("owner_uuid");
                if (!rs.wasNull()){
                    return result;
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getOwnerName(UUID holoId) {
        try {
            Connection conn = DB.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT owner_name from pholo WHERE holo_uuid='" + holoId.toString() + "'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String result = rs.getString("owner_name");
                if (!rs.wasNull()){
                    return result;
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
