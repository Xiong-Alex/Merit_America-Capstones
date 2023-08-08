import { Component } from "react";
import { connect } from "react-redux";
import { Redirect, Route, Switch, withRouter } from "react-router-dom";
import { addToken, deleteUser } from "../../Redux/actionCreators";
import Appbar from "../Appbar/Appbar";
import ChooseRestaraunt from "../ChooseRestaraunt/ChooseRestaraunt";
import DashBoard from "../Dashboard/Dashboard";
import Invite from "../Invite/Invite";
import LandingPage from "../LandingPage/LandingPage";
import Login from "../Login/Login";
import Register from "../Register/Register";
import VotingPage from "../VotingPage/VotingPage";

const mapStateToProps = (state) => {
	return {
		token: state.token,
		user: state.user,
	};
};

const mapDispatchToProps = (dispatch) => ({
	addToken: () => {
		dispatch(addToken());
	},
	deleteUser: () => {
		dispatch(deleteUser());
	},
});

class Routes extends Component {
	handleLogout = () => {
		this.props.addToken("");
		this.props.deleteUser();
	};

	render() {
		return (
			<div>
				<Appbar
					handleLogout={this.handleLogout}
					token={this.props.token.token}
				/>
				<Switch>
					<Route exact path='/' component={LandingPage} />
					<Route path='/login' component={Login} />
					<Route path='/register' component={Register} />
					<Route
						path='/chooserestaraunt'
						component={
							this.props.token.token !== undefined
								? () => <ChooseRestaraunt />
								: null
						}
					/>
					<Route
						exact
						path='/dashboard'
						component={
							this.props.token.token !== undefined ? () => <DashBoard /> : null
						}
					/>
					<Route path='/invitation/:id' component={VotingPage} />
					<Route
						exact
						path='/invite'
						component={
							this.props.token.token !== undefined ? () => <Invite /> : null
						}
					/>
					<Redirect to='/login' />
				</Switch>
			</div>
		);
	}
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Routes));
