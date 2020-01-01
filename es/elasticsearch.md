


# es 集群搭建

```
elasticsearch.bat -Ecluster.name=my_cluster -Enode.name=node_1 -Epath.data=D:\tmp\data1 -Ehttp.port=9200
elasticsearch.bat -Ecluster.name=my_cluster -Enode.name=node_2 -Epath.data=D:\tmp\data2 -Ehttp.port=9201

./bin/elasticsearch -d -p pid0 -Ebootstrap.system_call_filter=false -Enetwork.host=10.207.249.45 -Ediscovery.seed_hosts=10.207.249.45 -Ecluster.name=elasticsearch -Enode.name=node_0 -Epath.data=/export/servers/elasticsearch-7.1.0/logs0 -Ehttp.port=9200
./bin/elasticsearch -d -p pid1 -Ebootstrap.system_call_filter=false -Enetwork.host=10.207.249.45 -Ediscovery.seed_hosts=10.207.249.45 -Ecluster.name=elasticsearch -Enode.name=node_1 -Epath.data=/export/servers/elasticsearch-7.1.0/logs1 -Ehttp.port=9201

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


## 文档api


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


## search api
查询分url查询和 request body两种, 后者支持DSL, 支持的功能更丰富.
搜索结果是按相关度的算分排序的. 有时需要结合业务调整算分, 进而影响搜索结果.

## URI Search详解 3.7


## Request Body 与 Query DSL 3.8

- 需要通过 Kibana 导入Sample Data的电商数据。具体参考“2.2节-Kibana的安装与界面快速浏览”
- 需导入Movie测试数据，具体参考“2.4-Logstash安装与导入数据”
- match之间的term是or；match phrase的terms之间是and，并且term之间的关系也影响搜索结果，如果想提高搜索结果可以增加slot。

### Term Query, Phrase Query 的区别

https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-term-query.html
https://www.elastic.co/guide/en/elasticsearch/reference/7.5/query-dsl-match-query-phrase.html


## 自定义 mapping

mapping 的属性有很多参数, [详细的参见官网](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/mapping-params.html). 
下面列举几个.

|参数|解释|
|---|---|
|index:false |不可搜索|
|[null_value](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/null-value.html)|对空字段设置string值, 可用来搜索 null 值的 doc|
|[copy_to](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/copy-to.html)||

## 子字段
精确值和全文本的区别。
- 精确值不需要分词，如Apple store 就可以看成是精确词，一会会设置成keyword；
- 全文本需要分词搜索，所以一般会设置分词器和text类型。

## index template 3.13

 
## 聚合分析 3.14

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
- [meaning-of-relevancy](https://www.searchtechnologies.com/meaning-of-relevancy)
- https://baike.baidu.com/item/%E6%90%9C%E7%B4%A2%E5%BC%95%E6%93%8E%E5%8F%91%E5%B1%95%E5%8F%B2/2422574

- [search-uri-request](https://www.elastic.co/guide/en/elasticsearch/reference/7.0/search-uri-request.html)
- [search-search](https://www.elastic.co/guide/en/elasticsearch/reference/7.0/search-search.html)

- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/dynamic-mapping.html