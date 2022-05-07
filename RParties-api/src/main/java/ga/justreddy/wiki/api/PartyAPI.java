package ga.justreddy.wiki.api;

import ga.justreddy.wiki.api.database.Mongo;
import ga.justreddy.wiki.api.database.SQL;
import ga.justreddy.wiki.api.exceptions.PartyAPINotInitializedException;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public final class PartyAPI implements IPartyManager {

    private static boolean initialized = false;

    private static PartyAPI partyAPI;

    public static PartyAPI getPartyAPI() {
        if (partyAPI == null) partyAPI = new PartyAPI();
        return partyAPI;
    }

    /**
     * Make sure u add h2 yourself
     *
     * @param plugin
     */
    public static void initialize(JavaPlugin plugin) {
        initialized = true;
        SQL.connect("plugins/" + plugin.getDescription().getName() + "/data/database");
    }

    public static void initialize(String MongoURI) {
        initialized = true;
        Mongo.connect(MongoURI);
    }

    @Override
    public void createParty(UUID leader) {
        if (!initialized) throw new PartyAPINotInitializedException();
        if (Mongo.isMongoConnected()) {
            Document document = new Document("leader", leader)
                    .append("members", new ArrayList<>())
                    .append("muted", false);
            if (isInPartyLeader(leader) || isInPartyMember(leader)) {
                Bukkit.getPlayer(leader).sendMessage(ChatColor.translateAlternateColorCodes('&', " &cYou can't create a party while you're in a party"));
            } else {
                Mongo.getDatabase("parties").insertOne(document);
            }
        } else {
            if (isInPartyLeader(leader) || isInPartyMember(leader)) {
                Bukkit.getPlayer(leader).sendMessage(ChatColor.translateAlternateColorCodes('&', " &cYou can't create a party while you're in a party"));
            } else {
                SQL.update("INSERT INTO parties (leader, members, muted) VALUES ('" + leader + "', '" + "" + "', '" + false + "')");
            }
        }
    }

    @Override
    public void inviteMember(UUID leader, UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void inviteMemberBungee(UUID leader, UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void leaveParty(UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void leavePartyBungee(UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void warpPlayers(UUID leader) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void warpPlayersBungee(UUID leader) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void muteParty(UUID leader, boolean mute) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void disbandParty(UUID leader) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public boolean isInPartyMember(UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();
        if (Mongo.isMongoConnected()) {
            for (Document doc : Mongo.getDatabase("parties").find()) {
                if (doc.getList("members", String.class).contains(member.toString())) return true;
            }
        } else {
            try {
                ResultSet rs = SQL.getResult("SELECT * FROM parties WHERE laeder='" + member + "'");
                return rs.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInPartyLeader(UUID leader) {
        if (!initialized) throw new PartyAPINotInitializedException();
        if (Mongo.isMongoConnected()) {
            return Mongo.getDatabase("parties").find(new Document("leader", leader)).first() != null;
        } else {
            try {
                ResultSet rs = SQL.getResult("SELECT * FROM parties WHERE laeder='" + leader + "'");
                return rs.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return true;
            }
        }
    }

    @Override
    public Document getPartyByLeaderMongo(UUID leader) {
        if (!initialized) throw new PartyAPINotInitializedException();
        return null;
    }

    @Override
    public Document getPartyByMemberMongo(UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();
        return null;
    }

    @Override
    public String getPartyByLeaderSQL(UUID leader) {
        return "";
    }

    public String getPartyByMemberSQL(UUID member) {
        return "";
    }


    @Override
    public boolean isPartyLeader(UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();
        return false;
    }

    @Override
    public void kickMember(UUID inviter, UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void kickMemberBungee(UUID leader, UUID member) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void sendMessage(UUID sender, String message) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }

    @Override
    public void sendMessageBungee(UUID sender, String message) {
        if (!initialized) throw new PartyAPINotInitializedException();

    }
}
