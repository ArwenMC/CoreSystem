package com.arwenmc.database;

import com.arwenmc.CoreSys;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDB {

    CoreSys plugin;
    private String ip;
    private int port;
    private String database;
    private DBCollection players;
    private DB db;
    private MongoClient client;

    public MongoDB(CoreSys instance, String ip, int port) {
        plugin = instance;

        this.ip = ip;
        this.port = port;
    }

    public MongoDB(CoreSys instance, String ip) {
        plugin = instance;

        this.ip = ip;
        this.port = 27017; // MongoDB default port.
    }

    public void connect() {
        client = new MongoClient(this.ip, this.port);
        // database = client.getDB("severcore");
    }

}

/*

import java.net.UnknownHostException;
import java.util.UUID;

public class MongoDB {

    ServerCore plugin;

    public MongoDB(ServerCore instance) {
        plugin = instance;
    }

    private DBCollection players;
    private DB Main;
    private MongoClient client;


    public boolean connect(String ip, int port){
        //Connect to the specified ip and port
        //Default is localhost, 27017
        client = new MongoClient(ip, port);
        //Get the database called "Main"
        //If it does not exist it will be created automatically
        //once you save something in it
        Main = client.getDB("Main");
        //Get the collection called "players" in the database "mcserver"
        //Equivalent to the table in MySQL, you can store objects in here
        players = Main.getCollection("players");
        return true;
    }


    /**
     * Explanation for storePlayer
     *
     * @param uuid  Used to make sure player data is saved even in the event of name change.
     * @param name Adds verficiation to the uuid
     * @param rank   Rank will be pulled from either internal permission system or external.
     * @param pkills Counts how many kills the player has gotten (Named pkills incase we add mobkills)
     */

    /*public void storePlayer(UUID uuid, String name, String rank, int pkills){
        //This player has never played before and we just want to create a object for him
        DBObject obj = new BasicDBObject("uuid", uuid);
        obj.put("name", name);
        obj.put("rank", rank);
        obj.put("Player Kills", pkills);

        //Inserts Player Into Collection
        players.insert(obj);
    }
}*/
