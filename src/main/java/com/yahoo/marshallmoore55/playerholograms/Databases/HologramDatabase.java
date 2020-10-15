package com.yahoo.marshallmoore55.playerholograms.Databases;

import com.yahoo.marshallmoore55.playerholograms.Main;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class HologramDatabase {

    public HologramDatabase() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS pholo (holo_uuid CHAR(36) PRIMARY KEY, owner_uuid CHAR(36), owner_name CHAR(36))");
            ps.executeUpdate();
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

    public static void addHolo(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("INSERT INTO pholo VALUES(?,NULL,NULL)");
            ps.setString(1, holoId.toString());
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

    public static void removeHolo(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("DELETE FROM pholo WHERE holo_uuid='" + holoId.toString() + "'");
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

    public static void rentHolo(Player owner, UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("UPDATE pholo SET owner_uuid='" + owner.getUniqueId().toString() + "' , owner_name='" + owner.getDisplayName() + "' WHERE holo_uuid='" + holoId.toString() + "'");
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

    public static void unrentHolo(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("UPDATE pholo SET owner_uuid=NULL , owner_name=NULL WHERE holo_uuid='" + holoId.toString() + "'");
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

    public static boolean contains(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("SELECT holo_uuid from pholo");
            rs = ps.executeQuery();
            if (rs.next()) {
                do {
                    if (rs.getString("holo_uuid").equals(holoId.toString())) {
                        return true;
                    }
                } while (rs.next());
            }
            conn.close();
        } catch (SQLException e){
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

    public static boolean isOwned(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("SELECT owner_uuid from pholo WHERE holo_uuid='" + holoId.toString() + "'");
            rs = ps.executeQuery();
            if(rs.next()) {
                rs.getString("owner_uuid");
                if (!rs.wasNull()) {
                    return true;
                }
            }
            conn.close();
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

    public static String getOwner(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("SELECT owner_uuid from pholo WHERE holo_uuid='" + holoId.toString() + "'");
            rs = ps.executeQuery();
            if(rs.next()) {
                String result = rs.getString("owner_uuid");
                if (!rs.wasNull()){
                    return result;
                }
            }
            conn.close();
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
        return null;
    }

    public static String getOwnerName(UUID holoId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Main.getInstance().getDB().getConnection();
            ps = conn.prepareStatement("SELECT owner_name from pholo WHERE holo_uuid='" + holoId.toString() + "'");
            rs = ps.executeQuery();
            if(rs.next()) {
                String result = rs.getString("owner_name");
                if (!rs.wasNull()){
                    return result;
                }
            }
            conn.close();
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
        return null;
    }
}
