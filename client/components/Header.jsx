import Image from 'next/image';
import styled from 'styled-components';

const Nav = styled.nav`
	border-bottom: 2px solid #999999;
	min-width: 1100px;
	> div {
		width: 1100px;
		display: flex;
		margin: 0 auto;
		justify-content: space-between;
		padding: 20px;
		> span {
			display: flex;
			align-items: center;
		}
		.left-wrapper {
			p {
				font-weight: bold;
				font-size: 36px;
			}
			p:first-child {
				color: rgb(142, 68, 173);
			}
		}
		.right-wrapper {
			.icon-wrapper {
				width: 40px;
				margin-right: 16px;
			}
		}
	}
`;

const Header = () => {
	return (
		<Nav>
			<div>
				<span className="left-wrapper">
					<p>ToDoTo</p>
					<p>gether</p>
				</span>
				<span className="right-wrapper">
					<div className="icon-wrapper">
						<Image src={require('../public/img/icon_alarm.png')} alt="alarm" />
					</div>
					<div className="icon-wrapper">
						<Image
							src={require('../public/img/icon_person.png')}
							alt="person"
						/>
					</div>
					<p>logout</p>
				</span>
			</div>
		</Nav>
	);
};

export default Header;
