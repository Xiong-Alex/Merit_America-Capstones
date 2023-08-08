import GrainIcon from "@mui/icons-material/Grain";
import { CardContent, CardMedia, Link } from "@mui/material";
import Typography from "@mui/material/Typography";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import {
	OtherInfo,
	RestaurantBox,
	RestaurantStatus,
	RestaurantType,
} from "../../Containers/RestaurantCard/Styles";
import DiscoverCard from "../../Containers/RestaurantCard/useDiscover";
import { baseUrl } from "../../Shared/baseUrl";
import {
	CreateInvite,
	DashboardTitle,
	DiscoverList,
	InnerContainer,
	IntroContainer,
	MainBreadcrumb,
	PageContainer,
	PageHeader,
	RequestSectionTitle,
	RestarauntContainer,
	StartInviteLink,
	WelcomeCard,
	WelcomeCardContent,
	WelcomeMessage,
} from "./Styles";

const Dashboard = () => {
	const users = useSelector((state) => state.user);
	const token = useSelector((state) => state.token.token);
	const [request, setResquest] = useState([]);

	useEffect(() => {
		try {
			axios({
				method: "get",
				url: baseUrl + "/restaurant-tinder/invites",
				headers: {
					Authorization: `Bearer ${token}`,
				},
			})
				.then((res) => setResquest(res.data))
				.catch((error) => {
					throw error;
				});
		} catch {}
	}, [token]);

	return (
		<>
			<PageHeader>
				<DashboardTitle>Dashboard </DashboardTitle>
			</PageHeader>
			<PageContainer>
				{/* Breadcrumbs */}
				<MainBreadcrumb aria-label='breadcrumb'>
					<Link
						underline='hover'
						sx={{ display: "flex", alignItems: "center" }}
						color='inherit'
						href='/'
					>
						Home
					</Link>
					<Typography
						sx={{
							display: "flex",
							alignItems: "center",
							fontFamily: "Archivo",
						}}
						color='text.primary'
					>
						<GrainIcon sx={{ mr: 0.5 }} fontSize='inherit' />
						Dashboard
					</Typography>
				</MainBreadcrumb>
				<IntroContainer>
					<WelcomeCard variant='outlined'>
						<WelcomeCardContent>
							<WelcomeMessage>Welcome, {users.username}</WelcomeMessage>
						</WelcomeCardContent>
					</WelcomeCard>
					<WelcomeCard variant='outlined'>
						<WelcomeCardContent>
							<CreateInvite>
								<StartInviteLink to='/chooserestaraunt'>
									Start An Invite
								</StartInviteLink>
							</CreateInvite>
						</WelcomeCardContent>
					</WelcomeCard>
				</IntroContainer>
				<RestarauntContainer>
					<RequestSectionTitle> Your Requests </RequestSectionTitle>
					<InnerContainer>
						{request.map((info) => {
							//This is used to grab only the first restaurant
							//at the list so we can use it to grab a random image....
							const getFirst = [info.restaurants[0]];
							return (
								<>
									{getFirst?.map((item) => {
										return (
											<>
												<RestaurantBox sx={{ width: 345 }}>
													<CardMedia
														component='img'
														alt='restaurant-img'
														height='200'
														image={item.imgUrl}
														referrerPolicy='no-referrer'
													/>
													<OtherInfo>
														<RestaurantType> Requested By You </RestaurantType>
													</OtherInfo>
													<CardContent>
														<Typography color='text.secondary' variant='body1'>
															Restaurants to vote: {info.restaurants.length}
														</Typography>
													</CardContent>
												</RestaurantBox>
											</>
										);
									})}
								</>
							);
						})}
					</InnerContainer>
				</RestarauntContainer>
				<br />
				<RestarauntContainer>
					<RequestSectionTitle>Discover</RequestSectionTitle>
					<DiscoverList>
						<DiscoverCard /> {/* Randomly populates 8 restaurants for now*/}
					</DiscoverList>
				</RestarauntContainer>
			</PageContainer>
		</>
	);
};

export default Dashboard;
