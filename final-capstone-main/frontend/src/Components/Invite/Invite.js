import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addRestaurants, getLinks } from "../../Redux/actionCreators";
import { baseUrl } from "../../Shared/baseUrl";
import {
	Container,
	Form,
	FormSection,
	Input,
	InviteButton,
	LeftImage,
	LinkContainer,
	LinkSpan,
	Title,
} from "./Styles";

const Invite = () => {
	const dispatch = useDispatch();
	const [id, setId] = useState([]);
	const [link, setLink] = useState("");
	const token = useSelector((state) => state.token.token);

	//state for selected Restaurants...
	const selectRestaurants = useSelector(
		(state) => state.restaurants.selectedRestaurants
	);

	//state for invite form
	const [inviteInput, setInviteInput] = useState({
		email: [],
		decisionby: "",
		message: "",
	});

	//handles users input of the invite form...
	const handleChange = (event) => {
		event.preventDefault();
		setInviteInput((prevState) => ({
			...prevState,
			[event.target.name]: event.target.value,
		}));
	};

	console.log(selectRestaurants);

	//handles the submission of the form....
	const handleSubmit = (e) => {
		e.preventDefault();
		try {
			axios({
				method: "post",
				url: baseUrl + "/restaurant-tinder/addinvite",
				headers: {
					Authorization: `Bearer ${token}`,
				},
				data: {
					guestEmails: [inviteInput.email],
					restaurantIds: id,
					decisionBy: inviteInput.decisionby + "T15:53:19",
					message: inviteInput.message,
				},
			})
				.then((res) => getLink(res.data.inviteId))
				//clears selected restaurant list
				.then((res) => dispatch(addRestaurants([])))
				.then(() =>
					setInviteInput({
						email: "",
						decisionby: "",
						message: "",
					})
				)
				.catch((error) => {
					throw error;
				});
		} catch {}
	};

	const getLink = (id) => {
		try {
			axios({
				method: "get",
				url: baseUrl + `/restaurant-tinder/invitationlinks/${id}`,
				headers: {
					Authorization: `Bearer ${token}`,
				},
			})
				.then((res) => {
					return setLink(res.data[0].invitationLink);
				})
				.catch((error) => {
					throw error;
				});
		} catch {}
	};

	//temp fix: reformats for user to be able to access link.
	const shrinkLink = `http://localhost:3000/${link.substring(40)}`;

	useEffect(() => {
		selectRestaurants?.map((restaurantIds) => {
			return setId((prev) => [...prev, restaurantIds.restaurant.restaurantId]);
		});
		return id;
	}, []);

	return (
		<>
			<Container>
				<LeftImage src='https://images.pexels.com/photos/9746/people-mother-family-father.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1' />
				<FormSection>
					<Title>Invite Friends</Title>
					<Form>
						<Input
							type='text'
							name='email'
							placeholder='Email'
							onChange={handleChange}
							value={inviteInput.email}
						/>
						<Input
							type='date'
							name='decisionby'
							placeholder='Decision By:'
							onChange={handleChange}
							value={inviteInput.decisionby}
						/>
						<Input
							type='text'
							name='message'
							placeholder='Message'
							onChange={handleChange}
							value={inviteInput.message}
						/>
						<InviteButton
							color='primary'
							variant='contained'
							type='submit'
							onClick={handleSubmit}
						>
							Submit
						</InviteButton>
					</Form>
					<LinkContainer>
						<p>
							<LinkSpan>Grab The Link</LinkSpan>: {shrinkLink}
						</p>
						<p>
							<LinkSpan>Warning</LinkSpan>: Do not refresh or you will loose
							code
						</p>
					</LinkContainer>
				</FormSection>
			</Container>
		</>
	);
};

export default Invite;
