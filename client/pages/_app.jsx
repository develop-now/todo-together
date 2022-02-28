import Modal from 'react-modal';
import GlobalStyle from '../styles/GlobalStyle';
import { Providers, ProgressConsumer } from '../contexts';
import { Spinner } from '../components';

Modal.setAppElement('#__next');

const App = ({ Component, pageProps }) => {
	return (
		<>
			<GlobalStyle />
			<Providers>
				<Component {...pageProps} />
				<ProgressConsumer>
					{({ inProgress }) => {
						return inProgress && <Spinner />;
					}}
				</ProgressConsumer>
			</Providers>
		</>
	);
};

export default App;
