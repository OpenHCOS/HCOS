# coding=utf-8

"""
Omaha System 三大表格轉換成 JSON 格式
"""

import os
import uuid
import csv
import json

def write(filename, content):
    file = open(filename, 'w')
    file.write(content)
    file.close()


def main():
    #檔案開啟與輸出檔名設定
    csvFile1 = open('Omaha System 三大表格 - 42項問題表.csv', 'r')
    csvFile2 = open('Omaha System 三大表格 - 問題與徵兆對照表.csv', 'r')
    jsonFilename = 'Omaha System.json'
    
    
    #42項問題表處理
    list = {}
    listID = ""
    line1Bypass = False
    idAndName = []
    for row in csv.reader(csvFile1, delimiter=',', quotechar='"'):
        if line1Bypass == False:
            line1Bypass = True
            continue

        if listID != row[0]:
            listID = row[0]
            #面向名稱發生變化建立新的主項目
            #從 面向名稱 拆解出 面向代號 與 面向名稱
        
            idAndName = listID.split('.')
            data = {}
            data["name"] = idAndName[1]
            data["problems"] = {}
            list[idAndName[0]] = data
        
        #新增到主項目
        problem = {}
        problem["cht"] = row[1]
        problem["eng"] = row[3]
        problem["signs"] = {}
        
        index = len(list)-1
        list[idAndName[0]]["problems"][row[2]] = problem
        
    csvFile1.close()


    #問題與徵兆對照表處理
    list2 = []
    list2ID = ""
    line1Bypass = False
    
    for row in csv.reader(csvFile2, delimiter=',', quotechar='"'):
        if line1Bypass == False:
            line1Bypass = True
            continue
        sign = {}
        sign["cht"] = row[5]
        sign["eng"] = row[4]
        list[row[1]]["problems"][row[2]]["signs"][row[3]] = sign
        
    csvFile2.close()    


    #輸出 JSON 格式檔案
    jsonData = json.dumps(list, ensure_ascii=False)
    write(jsonFilename, jsonData)    

if __name__ == "__main__":
    main()
