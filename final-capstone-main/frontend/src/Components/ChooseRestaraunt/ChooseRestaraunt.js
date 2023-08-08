import GrainIcon from "@mui/icons-material/Grain";
import {
	Button,
	CardActions,
	CardContent,
	CardMedia,
	Link,
} from "@mui/material";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import Typography from "@mui/material/Typography";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { FeatureButton } from "../../Containers/Features/Styles";
import {
	OtherInfo,
	PhoneLink,
	RestaurantBox,
	RestaurantStatus,
	RestaurantType,
} from "../../Containers/RestaurantCard/Styles";
import UseRestaurant from "../../Containers/RestaurantCard/useRestaurant";
import { addRestaurants } from "../../Redux/actionCreators";
import { ClearButton, MainBreadcrumb, SubmitLink } from "../Dashboard/Styles";
import { ButtonsContainer } from "../Login/Styles";
import {
	Form,
	Header,
	Input,
	PageContainer,
	RestarauntForm,
	RestarauntList,
	SidebarSelected,
	Title,
} from "./Styles";

const ChooseRestaraunt = () => {
	const [searchInput, setSearchInput] = useState("");
	const [isOpen, setIsOpen] = useState(false);
	const selectRestaurants = useSelector(
		(state) => state.restaurants.selectedRestaurants
	);
	const dispatch = useDispatch();
	function handleChange(event) {
		setSearchInput(event.target.value);
	}

	return (
		<>
			<Header>
				<MainBreadcrumb aria-label='breadcrumb'>
					<Link
						underline='hover'
						sx={{ display: "flex", alignItems: "center" }}
						color='inherit'
						href='/'
					>
						Home
					</Link>
					<Link
						underline='hover'
						sx={{
							display: "flex",
							alignItems: "center",
							fontFamily: "Archivo",
						}}
						color='inherit'
						href='/dashboard'
					>
						Dashboard
					</Link>
					<Typography
						sx={{ display: "flex", alignItems: "center" }}
						color='text.primary'
					>
						<GrainIcon sx={{ mr: 0.5 }} fontSize='inherit' />
						Choose Restaurants
					</Typography>
				</MainBreadcrumb>
				<PageContainer>
					<RestarauntForm>
						<Title>Find Restaraunts</Title>
						<Form>
							<Input
								type='text'
								name='city'
								placeholder='City'
								onChange={handleChange}
								value={searchInput.city}
							/>
							<Input
								type='text'
								name='zip'
								placeholder='Zipcode'
								onChange={handleChange}
								value={searchInput.zip}
							/>
							<Input
								type='text'
								name='type'
								placeholder='Type'
								onChange={handleChange}
								value={searchInput.type}
							/>
							<FormControlLabel
								value='top'
								control={<Checkbox />}
								label='Show Hours'
								labelPlacement='start'
								onChange={() => setIsOpen(!isOpen)}
							/>
						</Form>
						<div>
							<hr />
							<Title>Selected Restaurants</Title>
							{selectRestaurants.map((restaurant) => {
								const getFirst = [restaurant.restaurant];
								return (
									<>
										{getFirst?.map((item) => {
											return (
												<RestaurantBox sx={{ width: 345 }}>
													<CardMedia
														component='img'
														alt='restaurant-img'
														height='200'
														image={item.imgUrl}
														referrerPolicy='no-referrer'
													/>
													<OtherInfo>
														<RestaurantType> {item.type} </RestaurantType>
													</OtherInfo>
													<CardContent>
														<Typography
															gutterBottom
															variant='h5'
															component='div'
														>
															{item.name}
														</Typography>

														<Typography variant='body2' color='text.secondary'>
															{item.street} {item.city}, {item.state}
															{item.zip}
														</Typography>
														<Typography variant='body2' color='text.secondary'>
															{item.phone}
														</Typography>
													</CardContent>
													<CardActions>
														<Button size='small'>
															<PhoneLink href={`tel: ${item.phone}`}>
																Call to Order
															</PhoneLink>
														</Button>
													</CardActions>
												</RestaurantBox>
											);
										})}
									</>
								);
							})}
							<ButtonsContainer>
								<FeatureButton color='primary' variant='contained'>
									<SubmitLink href='/invite'>Submit</SubmitLink>
								</FeatureButton>
							</ButtonsContainer>
						</div>
					</RestarauntForm>
					<div>
						<ClearButton onClick={() => dispatch(addRestaurants([]))}>
							Clear Selection
						</ClearButton>
						<RestarauntList>
							<UseRestaurant searchInput={searchInput} isOpen={isOpen} />
						</RestarauntList>
					</div>
				</PageContainer>
			</Header>
		</>
	);
};

export default ChooseRestaraunt;
