# consulting-time-open-data

data size:
          sample:108348
          variable:7
          
欄位說明：

      午別                 : 兩個時段    早上(36330)、下午(72018)

      weekday             :星期幾，共六天   1 、2、 3、 4、 5、 6
      
      動作                : 分五個類型 依類別分別標記1、2、3、4、5
			
      看診序號             : 看診序號
      
      等後時間             : 報到與等候時間差，單位(min)
      
      看診距開診時間       : 表定開診時間與看到診時間差，單位(min)
      
      醫師姓名            : 以代號標示，共計40位，從1號開始到第40 號

讀取資料

python:

            import pandas as pd
            df = pd.read_csv('consulting-time-open-data/Open.csv')
R

            data=read.csv("consulting-time-open-data/Open.csv")
