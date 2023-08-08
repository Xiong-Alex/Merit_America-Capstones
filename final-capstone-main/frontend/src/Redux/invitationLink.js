import * as ActionTypes from "./actionTypes";

export const InvitationLink = (
	state = {
		link: "",
	},

	action
) => {
	switch (action.type) {
		case ActionTypes.GET_LINK:
			return {
				...state,
				link: action.payload,
			};
		default:
			return state;
	}
};
