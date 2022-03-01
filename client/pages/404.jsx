import Head from 'next/head';
import styled from 'styled-components';

const Container = styled.div`
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	width: 1100px;
	margin-left: auto;
	margin-right: auto;
	justify-content: center;
	align-items: center;
	.text-title {
		color: #000000;
		font-weight: 500;
		font-size: 28px;
		margin: 40px 0;
	}
`;

const NotFound = () => {
	return (
		<Container>
			<Head>
				<title>페이지를 찾을 수 없습니다.</title>
			</Head>
			<p className="text-title">요청하신 페이지를 찾을 수 없습니다.</p>
		</Container>
	);
};

export default NotFound;
