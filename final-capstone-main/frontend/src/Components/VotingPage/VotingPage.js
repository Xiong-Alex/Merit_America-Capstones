import { faThumbsDown, faThumbsUp } from "@fortawesome/free-solid-svg-icons";
import {
	Button,
	CardActions,
	CardContent,
	CardMedia,
	Typography,
} from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { StyledIcon } from "../../Containers/Features/Styles";
import {
	OtherInfo,
	RestaurantBox,
	RestaurantType,
} from "../../Containers/RestaurantCard/Styles";
import { baseUrl } from "../../Shared/baseUrl";
import {
	Container,
	InformationContainer,
	SidebarTitle,
	ThumbsContainer,
	VotingContainer,
} from "./Styles";

const VotingPage = ({ match }) => {
	console.log("MATCH", match.params.id);
	const [infoData, setInfoData] = useState([]);
	const token = useSelector((state) => state.token.token);
	console.log(infoData);
	useEffect(() => {
		try {
			axios({
				method: "get",
				url: baseUrl + `/restaurant-tinder/invite/${match.params.id}`,
				headers: {
					Authorization: `Bearer ${token}`,
				},
			})
				.then((res) => {
					return setInfoData([res.data]);
				})
				.catch((error) => {
					throw error;
				});
		} catch {}
	}, []);
	return (
		<>
			<Container>
				{infoData?.map((data) => {
					console.log(data.decisionBy);
					return (
						<>
							<InformationContainer>
								<SidebarTitle>Information</SidebarTitle>
								<hr />
								<p>Created By: {data.createdBy.username}</p>
								<p>Decision By:{data.decisionBy} </p>
								<p>Message:{data.message} </p>
								<hr />
								<p>Group Members</p>
								{data.guestEmails.map((group) => {
									return (
										<>
											<p>{data.createdBy.email}</p>
											<p>{group.email}</p>
										</>
									);
								})}
							</InformationContainer>
							<VotingContainer>
								{data.restaurants.map((restaurant) => {
									return (
										<>
											<RestaurantBox sx={{ width: 345 }}>
												<CardMedia
													component='img'
													alt='restaurant-img'
													height='200'
													image={restaurant.imgUrl}
													referrerPolicy='no-referrer'
												/>
												<OtherInfo>
													<RestaurantType> {restaurant.type} </RestaurantType>
												</OtherInfo>
												<CardContent>
													<Typography gutterBottom variant='h5' component='div'>
														{restaurant.name}
													</Typography>

													<Typography variant='body2' color='text.secondary'>
														{restaurant.street} {restaurant.city},{" "}
														{restaurant.state}
														{restaurant.zip}
													</Typography>
													<Typography variant='body2' color='text.secondary'>
														{restaurant.phone}
													</Typography>
												</CardContent>
												<CardActions>
													<Button size='small'>
														<ThumbsContainer>
															<p>
																<StyledIcon
																	size='20px'
																	color='black'
																	icon={faThumbsUp}
																/>{" "}
															</p>
															<p>
																<StyledIcon
																	size='20px'
																	color='black'
																	icon={faThumbsDown}
																/>{" "}
															</p>
														</ThumbsContainer>
													</Button>
												</CardActions>
											</RestaurantBox>
										</>
									);
								})}
							</VotingContainer>
						</>
					);
				})}
			</Container>
		</>
	);
};

export default VotingPage;
