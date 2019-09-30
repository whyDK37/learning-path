


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