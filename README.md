# Crop-Recommendation
Agriculture plays a huge role in shaping livelihoods in India. As per 2018, agriculture employed more than 50℅ of the Indian work forces and contributed 17–18% to country's GDP. Agriculture is primary source of livelihood for 58% population in India. Farmers in India try to grow crops that have the highest price in the market. It is quite understandable seeing how majority of them barely earn enough to sustain themselves. However, in the long run it puts quite a strain on the farming land and will certainly decrease net profit in the years to come. Using CRS we wish to make the farmers realize how smart crop selection will help grow profits over time.

The application will recommend users what to grow based on where the user lives, soil type, weather patterns etc. It will also helps users calculate overall expenses and make user aware of current market prices.

## Scope
- We plan to restrict the application for farmers belonging to the state of Gujarat.
- Prices in agriculture domain are ever fluctuating. It will be difficult to finance to a very precise amount. We expect an error margin of around 5%.
- The application will be available in English and Gujarati.
- Farmer with a smartphone will be able to use our application.
- Application works perfectly fine with unstable internet connectivity.

## Software Design
### LOCATION TRACKING
User’s Current location and district would be fetched. If user is not of Gujarat state of India, user will be notified by alert message. This functionality will only work once location access is given to the application by the user.
![MapView](https://user-images.githubusercontent.com/46750877/111635723-ce35e600-881d-11eb-9dab-e058cbbb3917.png)

### RECOMMENDATION OF CROP
The District's values are passed to this fragment. In case location is turned off, user can manually enter any district/city name as long as its in the state of Gujarat. Recommendation Fragment as the name suggests will return back the best recommended crop that can be grown in that area, along with some additional info.

![Rec1](https://user-images.githubusercontent.com/46750877/111635974-0e956400-881e-11eb-9e4b-cccf0dd98298.png)
![Rec2](https://user-images.githubusercontent.com/46750877/111636003-1523db80-881e-11eb-9fad-f9760519eb81.png)

### BUDGET TRACKING OF THE CROP
You can pass on the values you just got from Recommendation Fragment into the Budget Tracker. If you do pass values, you would see fields like 'Buying Price', 'Seed Rate Ratio' and 'Seed Yield Ratio' already filled. Now all that the user has to do is to fill in rest of the details like Fertilizer Costs, Labour Costs, Water and Electricity Cost and the Size of the land in hectars. Total Budget and Income generated will be calculated and displayed in ruppees at the bottom of the screen.

![budget1](https://user-images.githubusercontent.com/46750877/111636293-59af7700-881e-11eb-9c19-a7ca3582de73.png)
![budget2](https://user-images.githubusercontent.com/46750877/111636317-5e742b00-881e-11eb-98f2-e0a68bdcdafd.png)
![budget3](https://user-images.githubusercontent.com/46750877/111636348-646a0c00-881e-11eb-81a4-d95d5840f803.png)

### ROTATION OF CROP
A common understanding is that its an unhealthy practice as farmers to grow the same crop time after time-again and again. To tackle this rhe crop rotation fragment will take in the value of your last crop planted and will return a new crop that will grow best in the same conditions where your previous crop had been grown.

![rotation1](https://user-images.githubusercontent.com/46750877/111637295-4bae2600-881f-11eb-91b2-9f9db0ed085a.png)
![rotation2](https://user-images.githubusercontent.com/46750877/111637301-4cdf5300-881f-11eb-9cbb-fa70fa45113d.png)

### GOVERMENT PROGRAMS/YOJNAS FOR FARMERS
The application will also display different Programms (yojanas) launched under govt initiatives in order to help support Agriculture Industry. A link would also be provided to access the official website.

![govt1](https://user-images.githubusercontent.com/46750877/111637474-78fad400-881f-11eb-988d-c2bdea244ba3.png)
![govt2](https://user-images.githubusercontent.com/46750877/111637478-7a2c0100-881f-11eb-9c6b-5c48339c96c8.png)
