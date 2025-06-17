import requests
url = "https://codeforces.com/api/contest.list"
data = requests.get(url).json()
from datetime import datetime,timedelta
contest=[]
for i in range(10) : 
    if(data["result"][i]["phase"] == "BEFORE") and any(item in data["result"][i]["name"] for item in ["Div. 3","Div. 4"]):   
        contest.append(data["result"][i])