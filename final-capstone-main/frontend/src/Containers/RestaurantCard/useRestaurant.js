import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { connect, useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getRestaurants } from '../../Redux/actionCreators';
import { baseUrl } from '../../Shared/baseUrl';
import RestaurantCard from './RestaurantCard';

const useRestaurant = ({ searchInput, isOpen }) => {
	const [isLoading, setIsLoading] = useState(false);

	const restaurants = useSelector((state) => state.restaurants.restaurants);
	const dispatch = useDispatch();

	const currentDate = new Date();

	const getHours = () => {
		const currentHours = `${currentDate.getHours()}:${currentDate.getMinutes()}:${currentDate.getSeconds()}`;
		return currentHours;
	};

	const getDays = (day) => {
		return currentDate.getDay(day);
	};

	//Using Async-Await method
	useEffect(() => {
		(async () => {
			try {
				setIsLoading(true);
				await axios
					.get(baseUrl + '/restaurant-tinder/restaurants')
					.then((res) => dispatch(getRestaurants(res.data)))
					.finally(() => setIsLoading(false));
			} catch (error) {
				console.log(error);
			}
		})();
	}, [dispatch]);

	const filterRestaurants = restaurants.filter((restaurant) => {
		return Object.values(restaurant)
			.join('')
			.toLowerCase()
			.includes(searchInput.toString().toLowerCase());
	});

	//checks only the specific day we are in...
	//Returns null for other days...
	const allRestaurantsHours = restaurants.map((restaurant) => {
		return restaurant.hoursOfOperation.map((hour, i) => {
			if (getDays() === i) {
				if (getHours() < hour.openFrom || getHours() > hour.openTo) {
					return 'closed';
				} else if (getHours() >= hour.openFrom || getHours() <= hour.openTo) {
					return 'open';
				}
			}
			return null;
		});
	});

	return (
		<>
			<RestaurantCard
				filterRestaurants={filterRestaurants}
				isOpen={isOpen}
				isLoading={isLoading}
				getHours={getHours}
				getDays={getDays}
				allRestaurantsHours={allRestaurantsHours}
			/>
		</>
	);
};

const mapDispatchToProps = (state) => ({
	restaraunts: state.restaraunts,
});

export default withRouter(connect(mapDispatchToProps)(useRestaurant));
