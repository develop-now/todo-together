import styled from 'styled-components';
import Footer from './Footer';
import Header from './Header';

const footer_height = 140;

const Container = styled.div`
	position: relative;
	min-height: 100vh;
	min-width: 1100px;
	display: flex;
	flex-direction: column;
`;

const Wrapper = styled.div`
	width: 100%;
	margin-bottom: ${footer_height}px;
	flex: 1;
	display: flex;
	flex-direction: column;
`;

const Content = styled.div`
	flex: 1;
	background-color: ${(props) => props.backgoundColor};
`;

const Layout = ({ children, backgroundColor = '#ffffff' }) => {
	return (
		<Container>
			<Wrapper>
				<Header />
				<Content backgroundColor={backgroundColor}>{children}</Content>
			</Wrapper>
			<Footer height={footer_height} />
		</Container>
	);
};

export default Layout;
