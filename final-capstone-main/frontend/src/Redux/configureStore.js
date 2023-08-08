import { applyMiddleware, combineReducers, compose, createStore } from "redux";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import thunk from "redux-thunk";
import { InvitationLink } from "./invitationLink";
import { Restaurant } from "./restaurant";
import { Token } from "./token";
import { User } from "./user";

const ConfigureStore = () => {
	const persistConfig = {
		key: "persist-key",
		storage,
	};

	//This enables the Redux Devtools to work properly...
	const composeEnhancers =
		window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

	const persistedReducer = persistReducer(
		persistConfig,
		combineReducers({
			token: Token,
			user: User,
			restaurants: Restaurant,
			link: InvitationLink,
		})
	);
	const store = createStore(
		persistedReducer,
		composeEnhancers(applyMiddleware(thunk))
	);
	return store;
};

export { ConfigureStore };
