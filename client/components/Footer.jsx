import styled from 'styled-components';

const Desktop = styled.footer`
	height: ${({ height }) => height}px;
	position: absolute;
	bottom: 0;
	width: 100%;
	background-color: rgb(249, 245, 250);
	.content-wrapper {
		width: 1100px;
		margin: 0 auto;
		position: relative;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		> p {
			color: rgb(190, 190, 190);
			font-size: 20px;
			line-height: 40px;
		}
	}
`;

const Footer = ({ height }) => {
	return (
		<Desktop height={height}>
			<div className="content-wrapper">
				<p>DinaPro</p>
				<p>Copyright © 2022 Jieun Ha. All rights reserved.</p>
			</div>
		</Desktop>
	);
};

export default Footer;
