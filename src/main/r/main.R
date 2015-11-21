install.packages("tm")

library(tm)

this.dir <- dirname(parent.frame(2)$ofile)
setwd(this.dir)

videos = read.csv("educatech.csv")

str(videos)

summary(videos)

corpus = Corpus(VectorSource(videos$description))

# Look at corpus
corpus

corpus[[1]]


# Convert to lower-case

corpus = tm_map(corpus, tolower)

corpus[[1]]

# IMPORTANT NOTE: If you are using the latest version of the tm package, you will need to run the following line before continuing (it converts corpus to a Plain Text Document). This is a recent change having to do with the tolower function that occurred after this video was recorded.

corpus = tm_map(corpus, PlainTextDocument)


# Remove punctuation

corpus = tm_map(corpus, removePunctuation)

corpus[[1]]

# Look at stop words
stopwords("french")[1:10]

# Remove stopwords and apple

corpus = tm_map(corpus, removeWords, c("apple", stopwords("french")))

corpus[[1]]

# Stem document

corpus = tm_map(corpus, stemDocument)

corpus[[1]]

frequencies = DocumentTermMatrix(corpus)

frequencies

# Look at matrix

inspect(frequencies[1000:1005,505:515])

# Check for sparsity

findFreqTerms(frequencies, lowfreq=20)

# Remove sparse terms

sparse = removeSparseTerms(frequencies, 0.995)
sparse

# Convert to a data frame

videosSparse = as.data.frame(as.matrix(sparse))

# Make all variable names R-friendly

colnames(videosSparse) = make.names(colnames(videosSparse))

# Add dependent variable

videosSparse$pedagogic = videos$pedagogic

# Split the data
