


# es 集群搭建

```
elasticsearch.bat -Ecluster.name=my_cluster -Enode.name=node_1 -Epath.data=D:\tmp\data1 -Ehttp.port=9200
elasticsearch.bat -Ecluster.name=my_cluster -Enode.name=node_2 -Epath.data=D:\tmp\data2 -Ehttp.port=9201

```
## 设置刷新时间
```
put index/_settings
{
   "index.refresh_interval" : "100s"  
}
```


## Index 相关 API
```
#查看索引相关信息
GET kibana_sample_data_ecommerce

#查看索引的文档总数
GET kibana_sample_data_ecommerce/_count

#查看前10条文档，了解文档格式
POST kibana_sample_data_ecommerce/_search
{
}

#_cat indices API
#查看indices
GET /_cat/indices/kibana*?v&s=index

#查看状态为绿的索引
GET /_cat/indices?v&health=green

#按照文档个数排序
GET /_cat/indices?v&s=docs.count:desc

#查看具体的字段
GET /_cat/indices/kibana*?pri&v&h=health,index,pri,rep,docs.count,mt

#How much memory is used per index?
GET /_cat/indices?v&h=i,tm&s=tm:desc

```


- 需要通过Kibana导入Sample Data的电商数据。具体参考“2.2节-Kibana的安装与界面快速浏览”

```
get _cat/nodes?v
GET /_nodes/es7_01,es7_02
GET /_cat/nodes?v
GET /_cat/nodes?v&h=id,ip,port,v,m


GET _cluster/health
GET _cluster/health?level=shards
GET /_cluster/health/kibana_sample_data_ecommerce,kibana_sample_data_flights
GET /_cluster/health/kibana_sample_data_flights?level=shards

#### cluster state
The cluster state API allows access to metadata representing the state of the whole cluster. This includes information such as
GET /_cluster/state

#cluster get settings
GET /_cluster/settings
GET /_cluster/settings?include_defaults=true

GET _cat/shards
GET _cat/shards?h=index,shard,prirep,state,unassigned.reason

```

## 文档api

```
#创建一个文档, es生成id
POST users/_doc
{
    "id":1
}

#指定id, 重复会报错
PUT users/_doc/1?p_type=create
{
"id":1
}

#获取文档
GET users/doc/1

#如果文档存在, 删除文档并创建新文档, 版本号+1
put users/_doc/1
{
    "id":2
}

#如果想增加属性, 需要用update api
POST users/_update/1
{
    "doc":{
        "post_date":"2019-12-18",
        "message":"update msg"
    }
}

#bulk api, 支持 index, create, update, delete, 每一条操作会有返回结果
#例子中创建的是library index 中的doc, 所以 index 行不需要指定 _index 了
PUT /library/_bulk?refresh
{"index":{"_id": "Leviathan Wakes"}}
{"name": "Leviathan Wakes", "author": "James S.A. Corey", "release_date": "2011-06-02", "page_count": 561}
{"index":{"_id": "Hyperion"}}
{"name": "Hyperion", "author": "Dan Simmons", "release_date": "1989-05-26", "page_count": 482}
{"index":{"_id": "Dune"}}
{"name": "Dune", "author": "Frank Herbert", "release_date": "1965-06-01", "page_count": 604}

#也可以在 index 行指定索引 library
PUT /_bulk?refresh
{"index":{"_index":"library","_id": "Leviathan Wakes"}}
{"name": "Leviathan Wakes", "author": "James S.A. Corey", "release_date": "2011-06-02", "page_count": 561}

#_mget 一次性获取多个index中的多个doc, 每个结果对应一个查询, 如果某个doc不存在, 返回 "found" : false
GET _mget
{
  "docs":[
    {
      "_index":"users",
      "_id":"1"
    },
    {
      "_index":"library",
      "_id":"Leviathan Wakes"
    }
  ]
}

#_msearch 批量查询每一行是一个search条件
POST _msearch
```

## 常见错误码
|错误|原因|
|---|---|
|无法连接| 网络故障或者集群挂了|
|连接无法关闭| 网络故障或节点出错|
|429| 集群过于繁忙|
|4XX| 请求格式错误|
|500| 集群内部错误|

## [analyzer 分词器](https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis.html)
分词器有三部分组成, 调用的顺序从上到下:

|组件|解释|
|---|---|
|Character Filters|针对原始文本处理，例如去除html,可多个|
|Tokenizer|按照规则切分单词|
|Token Filter|将切分的单词进行加工，小写，删除stopword，增加同义词等,可多个|


api 使用例子
```
#使用分词器来分析
GET /_analyze
{
    "analyzer":"standard",
    "text":"mastering elasticsearch, elasticsearch in Action"
}

#使用某个索引的某个字段测试分词
POST users/_analyze
{
  "field": "name",
  "text": "my-name"
}
#自定义分词器
GET /_analyze
{
  "tokenizer": "standard",
  "filter": ["lowercase"], 
  "char_filter": ["html_strip"], 
  "text":"<h1>mastering elasticsearch, elasticsearch in Action</h1>"
}

```

不同分词器的例子
```

#Simple Analyzer – 按照非字母切分（符号被过滤），小写处理
#Stop Analyzer – 小写处理，停用词过滤（the，a，is）
#Whitespace Analyzer – 按照空格切分，不转小写
#Keyword Analyzer – 不分词，直接将输入当作输出
#Patter Analyzer – 正则表达式，默认 \W+ (非字符分隔)
#Language – 提供了30多种常见语言的分词器
#2 running Quick brown-foxes leap over lazy dogs in the summer evening

#查看不同的analyzer的效果
#standard
GET _analyze
{
  "analyzer": "standard",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}

#simpe
GET _analyze
{
  "analyzer": "simple",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}


GET _analyze
{
  "analyzer": "stop",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}

#stop
GET _analyze
{
  "analyzer": "whitespace",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}

#keyword
GET _analyze
{
  "analyzer": "keyword",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}

GET _analyze
{
  "analyzer": "pattern",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}


#english
GET _analyze
{
  "analyzer": "english",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}


#需要安装插件
POST _analyze
{
  "analyzer": "icu_analyzer",
  "text": "他说的确实在理"
}


POST _analyze
{
  "analyzer": "standard",
  "text": "他说的确实在理”"
}


POST _analyze
{
  "analyzer": "icu_analyzer",
  "text": "这个苹果不大好吃"
}
```

## search api
查询分url查询和 request body两种, 后者支持DSL, 支持的功能更丰富.
搜索结果是按相关度的算分排序的. 有时需要结合业务调整算分, 进而影响搜索结果.

# URI Search详解
## 课程Demo
```

#基本查询, 指定字段
GET /movies/_search?q=2012&df=title&sort=year:desc&from=0&size=10&timeout=1s

#带profile
GET /movies/_search?q=2012&df=title
{
	"profile":"true"
}

#泛查询，正对_all,所有字段
GET /movies/_search?q=2012
{
	"profile":"true"
}

#指定字段
GET /movies/_search?q=title:2012&sort=year:desc&from=0&size=10&timeout=1s
{
	"profile":"true"
}

# 查找美丽心灵, Mind为泛查询
GET /movies/_search?q=title:Beautiful Mind
{
	"profile":"true"
}

# 泛查询
GET /movies/_search?q=title:2012
{
	"profile":"true"
}

#使用引号，Phrase查询, 两个查询条件必须同时出现,并且按顺序
GET /movies/_search?q=title:"Beautiful Mind"
{
	"profile":"true"
}

#分组，Bool查询
GET /movies/_search?q=title:(Beautiful Mind)
{
	"profile":"true"
}

#布尔操作符
# 查找美丽心灵
GET /movies/_search?q=title:(Beautiful AND Mind)
{
	"profile":"true"
}

# 查找美丽心灵
GET /movies/_search?q=title:(Beautiful NOT Mind)
{
	"profile":"true"
}

# 查找美丽心灵 %2B, 表示 + 
GET /movies/_search?q=title:(Beautiful %2BMind)
{
	"profile":"true"
}

#范围查询 ,区间写法
GET /movies/_search?q=title:beautiful AND year:[2002 TO 2018%7D
{
	"profile":"true"
}

#通配符查询
GET /movies/_search?q=title:b*
{
	"profile":"true"
}

//模糊匹配&近似度匹配
GET /movies/_search?q=title:beautifl~1
{
	"profile":"true"
}

GET /movies/_search?q=title:"Lord Rings"~2
{
	"profile":"true"
}
```


## 相关阅读

- 为什么不再支持单个Index下，多个Tyeps https://www.elastic.co/cn/blog/moving-from-types-to-typeless-apis-in-elasticsearch-7-0
- CAT Index API https://www.elastic.co/guide/en/elasticsearch/reference/7.1/cat-indices.html
- CAT Nodes API https://www.elastic.co/guide/en/elasticsearch/reference/7.1/cat-nodes.html
- Cluster API https://www.elastic.co/guide/en/elasticsearch/reference/7.1/cluster.html
- CAT Shards API https://www.elastic.co/guide/en/elasticsearch/reference/7.1/cat-shards.html

- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/indices-analyze.html
- https://www.elastic.co/guide/en/elasticsearch/reference/current/analyzer-anatomy.html

- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/search-search.html
- https://searchenginewatch.com/sew/news/2065080/search-engines-101
- https://www.huffpost.com/entry/search-engines-101-part-i_b_1104525
- https://www.entrepreneur.com/article/176398
- https://www.searchtechnologies.com/meaning-of-relevancy
- https://baike.baidu.com/item/%E6%90%9C%E7%B4%A2%E5%BC%95%E6%93%8E%E5%8F%91%E5%B1%95%E5%8F%B2/2422574

- https://www.elastic.co/guide/en/elasticsearch/reference/7.0/search-uri-request.html
- https://www.elastic.co/guide/en/elasticsearch/reference/7.0/search-search.html