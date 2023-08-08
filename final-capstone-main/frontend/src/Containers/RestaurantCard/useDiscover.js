import axios from "axios";
import React, { useEffect, useState } from "react";
import { connect, useDispatch, useSelector } from "react-redux";
import { withRouter } from "react-router-dom";
import { getRestaurants } from "../../Redux/actionCreators";
import { baseUrl } from "../../Shared/baseUrl";
import DiscoverCard from "./DiscoverCard";

const useDiscover = () => {
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
					.get(baseUrl + "/restaurant-tinder/restaurants")
					.then((res) => dispatch(getRestaurants(res.data)))
					.finally(() => setIsLoading(false));
			} catch (error) {
				console.log(error);
			}
		})();
	}, [dispatch]);

	// Randomizes Restaurants -----------------------------------------
	let DiscoverArray = [];
	let chosenRestaurant = new Set();

	function getRandomRestaurantId() {
		let max = restaurants.length;
		let min = 0;

		return Math.floor(Math.random() * (max - min) + min);
	}

	for (let i = 0; i < 8; i++) {
		//limiting it to 8 restaurants for now...
		let randomRestaurant = restaurants[getRandomRestaurantId()];
		if (chosenRestaurant.has(randomRestaurant)) {
			while (chosenRestaurant.has(randomRestaurant)) {
				randomRestaurant = restaurants[getRandomRestaurantId()];
			}
		}
		chosenRestaurant.add(randomRestaurant);
		DiscoverArray.push(randomRestaurant);
	}

	// -----------------------------------------------------

	const filterRestaurants = DiscoverArray.filter((restaurant) => {
		return Object.values(restaurant).join("").toLowerCase().includes("");
	});

	//checks only the specific day we are in...
	//Returns null for other days...
	const allRestaurantsHours = restaurants?.map((restaurant) => {
		return restaurant.hoursOfOperation?.map((hour, i) => {
			if (getDays() === i) {
				if (getHours() < hour.openFrom || getHours() > hour.openTo) {
					return "closed";
				} else if (getHours() >= hour.openFrom || getHours() <= hour.openTo) {
					return "open";
				}
			}
			return null;
		});
	});

	return (
		<>
			<DiscoverCard
				filterRestaurants={filterRestaurants}
				isLoading={isLoading}
				allRestaurantsHours={allRestaurantsHours}
			/>
		</>
	);
};

const mapDispatchToProps = (state) => ({
	restaraunts: state.restaraunts,
});

export default withRouter(connect(mapDispatchToProps)(useDiscover));
