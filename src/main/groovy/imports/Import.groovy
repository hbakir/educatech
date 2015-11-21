package imports

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.WriteConcern
import groovyx.net.http.RESTClient

def client = new RESTClient("http://www.rts.ch/")

def listParams = [path: "http://www.rts.ch/decouverte/monde-et-societe/histoire/?f=json/list&v=1.1"]
def listResponse = client.get(listParams)

println "videos count = " + listResponse.responseData.count

String mongoUri = System.getenv("MONGOLAB_URI");

if (mongoUri == null || "".equals(mongoUri)) {
    mongoUri = "mongodb://localhost:27017/educatech"
}
MongoClient mongoClient

try {
    MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
    dbName = mongoClientURI.getDatabase();
    mongoClient = new MongoClient(mongoClientURI)
} catch (UnknownHostException e) {
    println "MongoDB client failed: " + e.getMessage()
}

DB db = mongoClient.getDB(dbName)
DBCollection collection = db.getCollection("educatech")

for(video in listResponse.responseData.list) {

    println video.id + " " + video.title

    def videoDetailsParams = [path: "http://www.rts.ch/a/" + video.id + ".html?f=json/article&v=1.1"]
    def videoDetailsResponse = client.get(videoDetailsParams)

    def vignetteAttachment = videoDetailsResponse.responseData["related-content"].vignetteAttachment
    println vignetteAttachment.title

    video.numberOfPlays = videoDetailsResponse.responseData.numberOfPlays
    video.isCatchUp = videoDetailsResponse.responseData.isCatchUp
    video.homesection = videoDetailsResponse.responseData.homesection
    video.preview_image_url = videoDetailsResponse.responseData.preview_image_url
    video.durationS = videoDetailsResponse.responseData.durationS
    video.streams = videoDetailsResponse.responseData.streams

    BasicDBObject videoObject = new BasicDBObject("id", video.id)
    videoObject.append("title", video.title)
    videoObject.append("intro", video.intro)
    videoObject.append("url", video.url)
    videoObject.append("img", video.img)
    videoObject.append("creation", video.creation)
    videoObject.append("publication", video.publication)
    videoObject.append("expiration", video.expiration)
    videoObject.append("modification", video.modification)
    videoObject.append("program", video.program)
    videoObject.append("channel", video.channel)
    videoObject.append("plays", video.plays)
    videoObject.append("title", video.title)
    videoObject.append("isPlayable", video.isPlayable)
    videoObject.append("numberOfPlays", video.numberOfPlays)
    videoObject.append("isCatchUp", video.isCatchUp)
    videoObject.append("preview_image_url", video.preview_image_url)
    videoObject.append("durationS", video.durationS)
    videoObject.append("isCatchUp", video.isCatchUp)
    videoObject.append("isCatchUp", video.isCatchUp)

    try {
        collection.insert(videoObject, WriteConcern.SAFE);
    } catch (Exception e) {
        System.out.println("threw an exception: " + e.getClass() + " :: "
                + e.getMessage());
    }

}


