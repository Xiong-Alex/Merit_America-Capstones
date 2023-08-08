import Button from "@mui/material/Button";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import React, { useState } from "react";
import {
	Day,
	DiscoverBox,
	Hours,
	HoursContainer,
	OtherInfo,
	PhoneLink,
	RestaurantStatus,
	RestaurantType,
} from "./Styles";
const RestaurantCard = ({
	filterRestaurants,
	isOpen,
	isLoading,
	allRestaurantsHours,
}) => {
	let temp;
	/** @type {string[]} selectedRestaurant */
	const [selectedRestaurant, setSelectedRestaurant] = useState([]);

	/**
	 * @function - Handles when restaurants are selected
	 * @return {restaurant} - if selected is not true then return the restaurant
	 */
	const handleSelected = (id) => {
		filterRestaurants?.map((restaurant) => {
			if (restaurant.restaurantId === id) {
				console.log("selected", restaurant);
				setSelectedRestaurant([...selectedRestaurant, { restaurant }]);
			}
			return restaurant;
		});
	};

	console.log("selected Restaurant", selectedRestaurant);

	return (
		<>
			{isLoading && <p>Loading...</p>}
			{filterRestaurants?.map((restaurant) => {
				return (
					<>
						{!isLoading && (
							<DiscoverBox
								sx={{ width: 345 }}
								// onClick={(e) => {
								// 	e.preventDefault();
								// 	handleSelected(restaurant.restaurantId);
								// }}
								isclicked=''
							>
								<CardMedia
									component='img'
									alt='restaurant-img'
									height='200'
									image={restaurant.imgUrl}
									referrerPolicy='no-referrer'
								/>
								<OtherInfo>
									<RestaurantType> {restaurant.type} </RestaurantType>
									<RestaurantStatus>{temp}</RestaurantStatus>
								</OtherInfo>
								{allRestaurantsHours.map((item) => {
									temp = item;
								})}
								<CardContent>
									<Typography gutterBottom variant='h5' component='div'>
										{restaurant.name}
									</Typography>

									<Typography variant='body2' color='text.secondary'>
										{restaurant.street} {restaurant.city}, {restaurant.state}
										{restaurant.zip}
									</Typography>
									<Typography variant='body2' color='text.secondary'>
										{restaurant.phone}
									</Typography>

									{restaurant.hoursOfOperation?.map((hours) => {
										return (
											<>
												<section>
													{isOpen && (
														<HoursContainer>
															<Hours>
																<Day>{hours.dayOpen}</Day>
																{hours.openFrom} -{hours.openTo}
															</Hours>
														</HoursContainer>
													)}
												</section>
											</>
										);
									})}
								</CardContent>
							</DiscoverBox>
						)}
					</>
				);
			})}
		</>
	);
};

export default RestaurantCard;
