import csv
import justext
import re
import requests

"""
Scrapes rentjungle for text on apartments

To scrape a specific city, replace the city name in the request.
The city needs to have dashes instead of spaces.
Then for index, check rentjungle.com for the last index of the city and replace range below.

May update and include a better way to incorporate cities.
"""

"""
The following functions help filter out uneccessary text from the scraper.

Contain_int checks whether the line has an integer or not since 
address, distance, BA, BR, sq. ft and price all require an integer in some way or another.

not_links checks whether the line is an url or not.

not_description checks whether the line is just a randomly long description, which is uneccessary.

sq_ft checks whether the line has sq. ft or not.
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

"""
For the given link, the loop iterates from the first page to the last page of the website for the given city.
Then, we filter the lines by using the functions defined above and then remove additional uneccessary data if needed
by checking the length.
"""

apartments = []
apartment = []
count = 1
while True:
	try:
		for index in range(count,66):
			print("scraping " + str(index))
			count = index
			request = requests.get("https://www.rentjungle.com/santa-barbara-apartments-and-houses-for-rent/page:" + str(index) + "/", timeout=3)
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
		break
	except Exception as e:
		print(e)
		print(count)
		continue

# lines = []
# for line in apartments:
# 	if len(line[-1]) > 7 or "San Diego" not in line[0] or "Walk Score:" not in line[1] or "bathroom" not in line[3].lower():
# 		continue
# 	else:
# 		lines.append(line)

"""
writes the scraped data to a csv
"""

with open("housing_santa_barbara.csv", 'w', newline='') as text:
	data = csv.writer(text, delimiter=',')
	data.writerows(apartments)


