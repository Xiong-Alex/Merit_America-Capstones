import axios from "axios";
import { Component } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { addToken, addUser } from "../../Redux/actionCreators";
import { baseUrl } from "../../Shared/baseUrl";
import {
	ButtonsContainer,
	Container,
	FormContainer,
	ImageContainer,
	ImageLogin,
	InvalidCredentialsBox,
	LoginButton,
	LoginTitle,
	PasswordInput,
	SignupLink,
	Span,
	UsernameInput,
} from "./Styles";

const mapDispatchToProps = (dispatch) => ({
	addToken: () => dispatch(addToken()),
	addUser: () => dispatch(addUser()),
});

const initialState = {
	username: "",
	password: "",
	loginError: "",
};

let loginError = "";

class Login extends Component {
	constructor(props) {
		super(props);
		this.state = initialState;
		this.handleInputChange = this.handleInputChange.bind(this);
	}

	handleLogin = async () => {
		const data = {
			username: this.state.username,
			password: this.state.password,
			loginError: this.state.loginError,
		};

		axios
			.post(baseUrl + "/login", data)
			.then((res) => {
				this.props.history.push("/dashboard");
				this.props.dispatch(addToken(res.data.token));
				this.props.dispatch(addUser(res.data.user));
			})
			.catch((err) => {
				console.log(err.message);
				loginError = "Invalid Credentials";
				this.setState({ loginError });
			});
	};

	handleInputChange = (event) => {
		event.preventDefault();
		this.setState({
			[event.target.name]: event.target.value,
		});
	};

	render() {
		return (
			<>
				<Container>
					<ImageContainer>
						<picture>
							<ImageLogin
								src='https://images.pexels.com/photos/1640774/pexels-photo-1640774.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'
								alt='login'
							/>
						</picture>
					</ImageContainer>
					<FormContainer height='med'>
						<LoginTitle>
							log <Span>in</Span>
						</LoginTitle>
						<label className='sr-only'>Username</label>`
						<UsernameInput
							type='text'
							id='username'
							name='username'
							className='form-control'
							placeholder='Username'
							v-model='user.username'
							onChange={this.handleInputChange}
							required
						/>
						<label className='sr-only'>Password</label>
						<PasswordInput
							type='password'
							id='password'
							name='password'
							className='form-control'
							placeholder='Password'
							v-model='user.password'
							autocomplete='false'
							onChange={this.handleInputChange}
							required
						/>
						<InvalidCredentialsBox>
							{this.state.loginError}
						</InvalidCredentialsBox>
						<SignupLink to='/register'>Need an account?</SignupLink>
						<ButtonsContainer>
							<LoginButton
								color='primary'
								variant='contained'
								type='submit'
								onClick={this.handleLogin}
							>
								Login
							</LoginButton>
						</ButtonsContainer>
					</FormContainer>
				</Container>
			</>
		);
	}
}

export default withRouter(connect(mapDispatchToProps)(Login));
