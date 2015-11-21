#!/usr/bin/env bash

mongoexport -h ds047514.mongolab.com:47514 -d heroku_3dxw1cn1 -c educatech -u guest -p guest -o educatech.csv --csv -f _id,category,id,title,description,duration,fullLength,publishedDate,numberOfPlays,isCatchUp,program
