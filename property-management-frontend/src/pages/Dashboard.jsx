import { useRef } from "react";
import PropertyForm from "../components/PropertyForm";
import PropertyList from "../components/PropertyList";

const Dashboard = () => {
  const propertyListRef = useRef();

  return (
    <div className="page">
      <h2>Dashboard</h2>
      <PropertyForm onSuccess={() => propertyListRef.current.reload()} />
      <PropertyList ref={propertyListRef} />
    </div>
  );
};

export default Dashboard;
