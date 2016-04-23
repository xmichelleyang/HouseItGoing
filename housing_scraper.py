import csv
import justext
import re
import requests

"""
Scrapes rentjungle for text on apartments
"""

RE_D = re.compile('\d')
def contain_int(string):
	return RE_D.search(string) or string == "Studio"

def not_links(string):
	return not "http" in string

def not_description(string):
	return (not string.upper() == string or "$" in string) and not len(string) > 35  

def sq_ft(string):
	return "sq. ft" in string

apartments = []
apartment = []
for index in range(1,1264):
	print("scraping " + str(index))
	request = requests.get("https://www.rentjungle.com/san-diego-apartments-and-houses-for-rent/page:" + str(index) + "/")
	lines = justext.justext(request.content, justext.get_stoplist("English"))
	for i in range(len(lines)):
		if lines[i].text == "1":
			break
		if not_links(lines[i].text) and contain_int(lines[i].text) and not_description(lines[i].text):
			apartment.append(lines[i].text.replace(",", ""))
			if "$" in lines[i].text:
				if len(apartment) < 3 or not "sq." in apartment[-2]:
					apartment = []
					continue
				if len(apartment) < 5:
					apartment.insert(0, apartments[-1][1])
					apartment.insert(0, apartments[-1][0])
				if len(apartment) < 6:
					apartment = []
					continue
				while len(apartment) > 6:
					apartment.pop(0)
				apartments.append(apartment)
				apartment = []

with open("housing_san_diego.csv", 'w', newline='') as text:
	data = csv.writer(text, delimiter=',')
	data.writerows(apartments)


