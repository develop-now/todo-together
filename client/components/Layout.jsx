import styled from 'styled-components';
import Footer from './Footer';
import Header from './Header';

const footer_height = 140;

const Container = styled.div`
	position: relative;
	min-height: 100vh;
	min-width: 1100px;
`;

const Wrapper = styled.div`
	width: 100%;
	margin-bottom: ${footer_height - 1}px;
`;

const Layout = ({ children }) => {
	return (
		<Container>
			<Wrapper>
				<Header />
				{children}
			</Wrapper>
			<div style={{ height: '1px' }} />
			<Footer height={footer_height} />
		</Container>
	);
};

export default Layout;
