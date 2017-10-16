
import requests
import airtable as at

#改成你的airtable base id跟api key
base = at.Airtable('app45BrFVUK0L9MRt', 'keyzC6lOcxmorg7Lt')

#改成你的google geocoding api key
geoAPIKey = 'AIzaSyBNCkOUOd9Gn1EnuItvG3Bd1AM1S1eGeBE'

#改成你要把地址轉成經緯度的table名稱、住址欄位、經度欄位、緯度欄位
tableName = 'TestTable'
addrField = '設立地址'
latField = '緯度'
lngField = '經度'

# 從tableName資料表中讀取addrField的地址，經google geocode api轉成經緯度，再寫入latField,lngField欄位 
# addrField, latField, lngField 必須是已存在的欄位
def AddrToLatlng(tableName, addrField, latField, lngField):
    it_obj = base.iterate(tableName)
    for record in it_obj:
        addr = record['fields'][addrField]
        url = 'https://maps.googleapis.com/maps/api/geocode/json'
        url += '?address='+addr
        url += '&key='+geoAPIKey
        #print(url)
        
        r = requests.get(url)
        if r.status_code == requests.codes.all_okay:
            geocode = r.json()['results'][0]
            location = geocode['geometry']['location']
            #print('lat: '+str(location['lat'])+', lng: '+str(location['lng']))
            
            recordID = record['id']
            #print(recordID)
            latlng = {}
            latlng[latField] = str(location['lat'])
            latlng[lngField] = str(location['lng'])
            edited = base.update(tableName, recordID, latlng)
            print(edited)
    

AddrToLatlng(tableName, addrField, latField, lngField)