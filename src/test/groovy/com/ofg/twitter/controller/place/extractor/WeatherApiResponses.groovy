package com.ofg.twitter.controller.place.extractor

class WeatherApiResponses {

    public static final String CITY_FOUND = '''
                                                            {
                                                              "coord": {
                                                                "lon": -77,
                                                                "lat": 38
                                                              },
                                                              "sys": {
                                                                "type": 1,
                                                                "id": 2884,
                                                                "message": 0.0383,
                                                                "country": "US",
                                                                "sunrise": 1407060798,
                                                                "sunset": 1407111297
                                                              },
                                                              "weather": [
                                                                {
                                                                  "id": 501,
                                                                  "main": "Rain",
                                                                  "description": "moderate rain",
                                                                  "icon": "10n"
                                                                },
                                                                {
                                                                  "id": 701,
                                                                  "main": "Mist",
                                                                  "description": "mist",
                                                                  "icon": "50n"
                                                                },
                                                                {
                                                                  "id": 741,
                                                                  "main": "Fog",
                                                                  "description": "fog",
                                                                  "icon": "50n"
                                                                }
                                                              ],
                                                              "base": "cmc stations",
                                                              "main": {
                                                                "temp": 294.11,
                                                                "pressure": 1018,
                                                                "humidity": 94,
                                                                "temp_min": 292.15,
                                                                "temp_max": 297.04
                                                              },
                                                              "wind": {
                                                                "speed": 1.5,
                                                                "deg": 80
                                                              },
                                                              "rain": {
                                                                "1h": 2.54
                                                              },
                                                              "clouds": {
                                                                "all": 90
                                                              },
                                                              "dt": 1407059885,
                                                              "id": 4788822,
                                                              "name": "Tappahannock",
                                                              "cod": 200
                                                            }
'''

    public static final String CITY_NOT_FOUND = '''
                                                            {
                                                              "message": "Error: Not found city",
                                                              "cod": "404"
                                                            }
'''
}
