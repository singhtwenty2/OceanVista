import { BadgeDelta, Button, Card } from '@tremor/react';
import { Store, TrendingUp, Users, Star } from 'lucide-react';
import { mockServices, mockBusinessStats } from '../../data/mockData';

export const Business = () => {
  return (
    <div className="p-6 space-y-6">
      {/* Stats Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <Card>
          <Card className="flex flex-row items-center justify-between pb-2">
            <Card className="text-sm font-medium">Total Services</Card>
            <Store className="h-4 w-4 text-gray-500" />
          </Card>
          <Card>
            <div className="text-2xl font-bold">{mockBusinessStats.totalServices}</div>
            <p className="text-xs text-gray-500">
              {mockBusinessStats.activeServices} active
            </p>
          </Card>
        </Card>

        <Card>
          <Card className="flex flex-row items-center justify-between pb-2">
            <Card className="text-sm font-medium">Monthly Earnings</Card>
            <TrendingUp className="h-4 w-4 text-gray-500" />
          </Card>
          <Card>
            <div className="text-2xl font-bold">
              ₹{mockBusinessStats.totalEarnings.toLocaleString()}
            </div>
            <div className="flex items-center">
              <BadgeDelta deltaType="increase" size="sm">
                {mockBusinessStats.monthlyGrowth}%
              </BadgeDelta>
            </div>
          </Card>
        </Card>

        <Card>
          <Card className="flex flex-row items-center justify-between pb-2">
            <Card className="text-sm font-medium">Average Rating</Card>
            <Star className="h-4 w-4 text-gray-500" />
          </Card>
          <Card>
            <div className="text-2xl font-bold">{mockBusinessStats.averageRating}</div>
            <p className="text-xs text-gray-500">From all services</p>
          </Card>
        </Card>

        <Card>
          <Card className="flex flex-row items-center justify-between pb-2">
            <Card className="text-sm font-medium">Pending Approvals</Card>
            <Users className="h-4 w-4 text-gray-500" />
          </Card>
          <Card>
            <div className="text-2xl font-bold">{mockBusinessStats.pendingApprovals}</div>
            <p className="text-xs text-gray-500">Services awaiting review</p>
          </Card>
        </Card>
      </div>

      {/* Services List */}
      <Card>
        <Card>
          <Card>Your Services</Card>
        </Card>
        <Card>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {mockServices.map((service) => (
              <div
                key={service.id}
                className="border rounded-lg overflow-hidden shadow-sm"
              >
                <img
                  src={service.image}
                  alt={service.name}
                  className="w-full h-48 object-cover"
                />
                <div className="p-4">
                  <div className="flex items-center justify-between mb-2">
                    <h3 className="font-semibold">{service.name}</h3>
                    <span className={`
                      px-2 py-1 rounded-full text-xs
                      ${service.status === 'active' 
                        ? 'bg-green-100 text-green-800' 
                        : 'bg-yellow-100 text-yellow-800'}
                    `}>
                      {service.status}
                    </span>
                  </div>
                  <p className="text-sm text-gray-500 mb-2">{service.category}</p>
                  <div className="flex items-center gap-2 mb-2">
                    <Star className="h-4 w-4 text-yellow-400" />
                    <span className="text-sm">{service.rating}</span>
                    <span className="text-sm text-gray-500">
                      ({service.totalReviews} reviews)
                    </span>
                  </div>
                  <div className="text-sm text-gray-500 mb-4">
                    {service.location} • {service.distance}
                  </div>
                  <div className="flex items-center justify-between">
                    <span className="text-sm font-medium">
                      Monthly Revenue
                    </span>
                    <span className="font-semibold">
                      ₹{service.monthlyEarnings.toLocaleString()}
                    </span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </Card>
      </Card>

      {/* Add Service Button */}
      <div className="flex justify-end">
        <Button className="bg-primary-500 text-white">
          Add New Service
        </Button>
      </div>
    </div>
  );
};