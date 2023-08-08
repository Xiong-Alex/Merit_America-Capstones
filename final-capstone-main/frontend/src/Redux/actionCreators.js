import * as ActionTypes from "./actionTypes";

export const addToken = (token) => ({
	type: ActionTypes.ADD_TOKEN,
	payload: token,
});

export const addUser = (user) => ({
	type: ActionTypes.ADD_USER,
	payload: user,
});

export const deleteUser = () => ({
	type: ActionTypes.DELETE_USER,
});

//Restaurants
export const getRestaurants = (restaurant) => ({
	type: ActionTypes.GET_RESTAURANTS,
	payload: restaurant,
});

export const addRestaurants = (selected) => ({
	type: ActionTypes.ADD_SELECTED_RESTAURANTS,
	payload: selected,
});

export const getLinks = (link) => ({
	type: ActionTypes.GET_LINK,
	payload: link,
});
