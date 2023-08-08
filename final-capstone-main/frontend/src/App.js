import '@fontsource/archivo';
import '@fontsource/black-han-sans';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import { CssBaseline } from '@mui/material';
import { ThemeProvider } from '@mui/material/styles';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';
import Main from './Components/Routes/Routes';
import { ConfigureStore } from './Redux/configureStore';
import { theme } from './Themes/MainTheme';

const store = ConfigureStore();

function App() {
	return (
		<Provider store={store}>
			<CssBaseline />
			<BrowserRouter>
				<ThemeProvider theme={theme}>
					<PersistGate persistor={persistStore(store)}>
						<Main />
					</PersistGate>
				</ThemeProvider>
			</BrowserRouter>
		</Provider>
	);
}

export default App;
