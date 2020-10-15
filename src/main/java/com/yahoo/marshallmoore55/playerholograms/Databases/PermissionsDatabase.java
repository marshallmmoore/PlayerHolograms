package com.yahoo.marshallmoore55.playerholograms.Databases;

import com.yahoo.marshallmoore55.playerholograms.Main;

import java.sql.*;
import java.util.UUID;

public class PermissionsDatabase {

    public PermissionsDatabase() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pholoPerm (admin_uuid CHAR(36) PRIMARY KEY)");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void addAdmin(UUID admin_uuid) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("INSERT INTO pholoPerm VALUES(?)");
            ps.setString(1, admin_uuid.toString());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void removeAdmin(UUID admin_uuid) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("DELETE FROM pholoPerm WHERE admin_uuid='" + admin_uuid.toString() + "'");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }


    public static boolean isAdmin(UUID player_uuid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("SELECT admin_uuid from pholoPerm");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("admin_uuid").equals(player_uuid.toString())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }


}
