// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //存放轮播图数据的列表
    swiperList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getSwiperList()
  },

  //获取轮播图数据的方法
  getSwiperList() {
    const token = wx.getStorageSync('token');
    wx.request({
      url: 'http://localhost:8080/home/slides',
      method: 'GET',
      header: {
        'content-type': 'application/json',
        'Authorization': `Bearer ${token}` // 将JWT令牌添加到请求头
      },
      success: (res) => {
        console.log(res)
        this.setData({
          swiperList: res.data.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})