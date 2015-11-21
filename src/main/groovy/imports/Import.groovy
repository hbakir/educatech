package imports

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.WriteConcern
import groovyx.net.http.RESTClient

def client = new RESTClient("http://www.rts.ch/")
def srgssrclient = new RESTClient("http://il.srgssr.ch/")
def categories = ["histoire", "science", "geographie", "math", "arts", "musique", "litterature"]
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
collection.drop()

for (category in categories) {

// def listParams = [path: "http://www.rts.ch/decouverte/monde-et-societe/histoire/?f=json/list&v=1.1"]

    def listParams = [path: "http://il.srgssr.ch/integrationlayer/1.0/ue/rts/video/search.json?q=" + category + "&pageSize=100&pageNumber=1"]

    def listResponse = srgssrclient.get(listParams)
    def searchResults = listResponse.responseData.SearchResults

    println "videos count = " + searchResults["@total"]

    for (video in searchResults.SearchResult) {

        println video.id + " " + video.urn + " " + video.title

        def videoDetailsParams = [path: "http://www.rts.ch/a/" + video.id + ".html?f=json/article&v=1.1"]
        def videoDetailsResponse = client.get(videoDetailsParams)

        def vignetteAttachment = videoDetailsResponse.responseData["related-content"].vignetteAttachment
        println vignetteAttachment.title

        def parentBroadcastAttachment = videoDetailsResponse.responseData["related-content"].parentBroadcastAttachment
        println "program = " + parentBroadcastAttachment.program

        video.numberOfPlays = videoDetailsResponse.responseData.numberOfPlays
        video.url = videoDetailsResponse.responseData.url
        video.isCatchUp = videoDetailsResponse.responseData.isCatchUp
        video.homesection = videoDetailsResponse.responseData.homesection
        video.preview_image_url = videoDetailsResponse.responseData.preview_image_url
        video.durationS = videoDetailsResponse.responseData.durationS
        video.streams = videoDetailsResponse.responseData.streams
        video.program = parentBroadcastAttachment.program

        BasicDBObject videoObject = new BasicDBObject("_id", video.id)

        videoObject.append("category", category)
        videoObject.append("id", video.id)
        videoObject.append("title", video.title)
        videoObject.append("description", video.description)
        videoObject.append("duration", video.duration)
        videoObject.append("fullLength", video.fullLength)
        videoObject.append("publishedDate", video.publishedDate)
        videoObject.append("img", video.img)
        videoObject.append("url", video.url)

        videoObject.append("thumbnail", vignetteAttachment.thumbnail[0])
        videoObject.append("numberOfPlays", video.numberOfPlays)
        videoObject.append("isCatchUp", video.isCatchUp)
        videoObject.append("homesection", video.homesection)
        videoObject.append("preview_image_url", video.preview_image_url)
        videoObject.append("durationS", video.durationS)
        videoObject.append("streams_hds", video.streams.hds)
        videoObject.append("streams_hds_sd", video.streams.hds_sd)
        videoObject.append("streams_hls", video.streams.hls)
        videoObject.append("streams_hls_sd", video.streams.hls_sd)
        videoObject.append("program", video.program[0])
        videoObject.append("pedagogic", false)

        try {
            collection.insert(videoObject, WriteConcern.SAFE);
        } catch (Exception e) {
            println "threw an exception: " + e.getClass() + " :: "
            +e.getMessage()
        }

    }
}


