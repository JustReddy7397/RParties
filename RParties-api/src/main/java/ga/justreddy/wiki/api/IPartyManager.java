package ga.justreddy.wiki.api;

import org.bson.Document;

import java.util.UUID;

interface IPartyManager {

    void createParty(UUID leader);

    void inviteMember(UUID leader, UUID member);

    void inviteMemberBungee(UUID leader, UUID member);

    void leaveParty(UUID member);

    void leavePartyBungee(UUID member);

    void warpPlayers(UUID leader);

    void warpPlayersBungee(UUID leader);

    void muteParty(UUID leader, boolean mute);

    void disbandParty(UUID leader);

    boolean isInPartyMember(UUID member);

    boolean isInPartyLeader(UUID leader);

    void getPartyByLeader(UUID leader);

    void getPartyByMember(UUID member);

    boolean isPartyLeader(UUID member);

    void kickMember(UUID inviter, UUID member);

    void kickMemberBungee(UUID leader, UUID member);

    void sendMessage(UUID sender, String message);

    void sendMessageBungee(UUID sender, String message);


}
