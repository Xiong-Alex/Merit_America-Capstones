import Button from "@mui/material/Button";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { addRestaurants } from "../../Redux/actionCreators";
import {
	Day,
	Hours,
	HoursContainer,
	OtherInfo,
	PhoneLink,
	RestaurantBox,
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

	const selectRestaurants = useSelector(
		(state) => state.restaurants.selectedRestaurants
	);
	const dispatch = useDispatch();

	const handleSelected = (id) => {
		filterRestaurants?.map((restaurant) => {
			if (restaurant.restaurantId === id) {
				selectRestaurants
					? dispatch(addRestaurants([...selectRestaurants, { restaurant }]))
					: dispatch(addRestaurants([{ restaurant }]));
			}
			return restaurant;
		});
	};
	return (
		<>
			{isLoading && <p>Loading...</p>}
			{filterRestaurants.map((restaurant) => {
				return (
					<>
						{!isLoading && (
							<RestaurantBox
								sx={{ width: 345 }}
								onClick={(e) => {
									e.preventDefault();
									handleSelected(restaurant.restaurantId);
								}}
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

									{restaurant.hoursOfOperation.map((hours) => {
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
							</RestaurantBox>
						)}
					</>
				);
			})}
		</>
	);
};

export default RestaurantCard;
