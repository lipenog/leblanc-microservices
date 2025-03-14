curl -X PUT "http://localhost:9200/posts" -H "Content-Type: application/json" -u elastic:elastic -d '
{
  "settings": {
    "analysis": {
      "analyzer": {
        "analyzer_for_content": {
          "type": "custom",
          "tokenizer": "standard",
          "filter": [
            "asciifolding",
            "lowercase",
            "snowball"
          ]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "id": {
        "type": "keyword"
      },
      "content": {
        "type": "text",
        "analyzer": "analyzer_for_content"
      },
      "media_content": {
        "type": "text",
        "analyzer": "analyzer_for_content"
      }
    }
  }
}'
