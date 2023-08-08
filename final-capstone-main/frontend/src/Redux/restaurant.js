import * as ActionTypes from "./actionTypes";

export const Restaurant = (
	state = {
		restaurants: [],
		selectedRestaurants: [],
	},

	action
) => {
	switch (action.type) {
		case ActionTypes.GET_RESTAURANTS:
			return {
				...state,
				restaurants: action.payload,
			};
		case ActionTypes.ADD_SELECTED_RESTAURANTS:
			return {
				...state,
				selectedRestaurants: action.payload,
			};
		default:
			return state;
	}
};
